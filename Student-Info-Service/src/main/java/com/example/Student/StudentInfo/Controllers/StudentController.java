package com.example.Student.StudentInfo.Controllers;

import com.example.Student.StudentInfo.DTOs.StudentDTO;
import com.example.Student.StudentInfo.Interfaces.StudentInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class StudentController {

    @Autowired
    private StudentInterface studentInterface;


    @RequestMapping(value = "/students-info", method = RequestMethod.GET)
    public ResponseEntity<?> getAllStudentDetails(){

        return studentInterface.getAllStudentDetails();
    }

    @RequestMapping(value = "/student/{id}", method = RequestMethod.PUT)
    public String updateStudentDetails(@PathVariable("id") UUID studentId, @RequestBody StudentDTO student){

        return studentInterface.updateStudentDetails(studentId, student.getStudentName());
    }

    @RequestMapping(value = "/student/{id}", method = RequestMethod.DELETE)
    public String deleteStudent(@PathVariable("id") UUID studentId){

        return studentInterface.deleteStudent(studentId);
    }

}