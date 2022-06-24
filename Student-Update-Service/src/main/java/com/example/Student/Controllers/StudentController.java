package com.example.Student.Controllers;

import com.example.Student.Entities.Student;
import com.example.Student.Interfaces.StudentInfoInterface;
import com.example.Student.Interfaces.StudentInterface;
import com.example.Student.Repositories.StudentRepository;
import com.example.Student.Services.StudentServices;
import com.example.Student.DTOs.StudentDTO;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Autowired
    private StudentInfoInterface studentInfoInterface;

    @Autowired
    private StudentInterface studentInterface;


    //Get Student by ID
    @RequestMapping(value = "/student/{id}", method = RequestMethod.GET)
    @Cacheable(value = "Student" , key = "#studentId")
    public ResponseEntity<?> readStudentDetails(@PathVariable("id") UUID studentId){

        return studentInterface.readStudentDetails(studentId);
    }


    //Create a new student in database
    @RequestMapping(value = "/student", method = RequestMethod.POST)
    public ResponseEntity<?> createStudent(@RequestBody StudentDTO student){

        return studentInterface.createStudent(student.getStudentName());
    }

    //Sends request to other microservice for updating the student
    @RequestMapping(value = "/student/{id}", method = RequestMethod.PUT)
    @CircuitBreaker(name = "studentService", fallbackMethod = "alternateToUpdate")
    @CachePut(value = "Student", key = "#studentId")
    public ResponseEntity<?> updateStudentDetails(@PathVariable("id") UUID studentId, @RequestBody StudentDTO student){

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<StudentDTO> httpEntity = new HttpEntity<>(student, headers);
        restTemplate.exchange("http://student-info-service/api/student/" + studentId
                ,HttpMethod.PUT, httpEntity, String.class);

        return new ResponseEntity<>(studentRepository.findByStudentId(studentId), HttpStatus.OK);
    }


    //Fallback method for update students
    public ResponseEntity<?> alternateToUpdate(Exception e){
        System.out.println(e.getMessage());
        return new ResponseEntity<>("Can't Update at the moment.", HttpStatus.OK);

    }


    //Sends request to other microservice for deleting the student
    @RequestMapping(value = "/student/{id}", method = RequestMethod.DELETE)
    @CircuitBreaker(name = "studentService", fallbackMethod = "alternateToDelete")
    @CacheEvict(value = "Student" ,key="#studentId", allEntries = true)
    public String deleteStudent(@PathVariable("id") UUID studentId){

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Student> httpEntity = new HttpEntity<Student>(headers);
        return restTemplate.exchange("http://student-info-service/api/student/"
                + studentId, HttpMethod.DELETE, httpEntity, String.class).getBody();
    }


    //Fallback method for deleting a student
    public String alternateToDelete(Exception e){
        return "Can't delete at the moment";
    }


    //Getting response for the last task using WebClient
    @RequestMapping(value = "/response", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getResponse(@RequestParam Map<String, String> request){

        String response = webClientBuilder.build()
                .post()
                .uri("https://info.payu.in/merchant/postservice.php?form=2")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromFormData("key", request.get("key"))
                .with("command", request.get("command"))
                .with("var1", request.get("var1"))
                .with("hash", request.get("hash")))
                .retrieve()
                .bodyToMono(String.class).block();

        return new ResponseEntity<>(response, HttpStatus.OK);

    }


    //Get all students using OpenFeign
    @RequestMapping(path = "/getAll", method = RequestMethod.GET)
    public List<Student> getAllStudents(){
        return studentInfoInterface.getAllStudents();
    }

}
