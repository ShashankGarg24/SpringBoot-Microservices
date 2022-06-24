package com.example.Student.StudentInfo.Services;

import com.example.Student.StudentInfo.Entities.Student;
import com.example.Student.StudentInfo.Interfaces.StudentInterface;
import com.example.Student.StudentInfo.Repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class StudentServices implements StudentInterface {

    @Autowired
    private StudentRepository studentRepository;

    //Update Student by ID
    public String updateStudentDetails(UUID id, String newName){
        try{
            Student student = studentRepository.findByStudentId(id);
            student.setStudentName(newName);
            studentRepository.save(student);

            return "Student Updated";
        }
        catch (Exception e){
            return "Student Not Updated";
        }
    }

    //Delete Student by ID
    public String deleteStudent(UUID id){
        try{
            studentRepository.deleteStudent(id);
            return "Student Deleted";
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return "Student Not Deleted";
        }
    }

    //Get List of all available students
    public ResponseEntity<?> getAllStudentDetails() {
        try{
            System.out.println("db");
            return new ResponseEntity<>(studentRepository.findAll(), HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
