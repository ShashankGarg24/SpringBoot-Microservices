package com.example.Student.Services;

import com.example.Student.Entities.Student;
import com.example.Student.Interfaces.StudentInterface;
import com.example.Student.Repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class StudentServices implements StudentInterface {

    @Autowired
    private StudentRepository studentRepository;


    //Create new student
    public ResponseEntity<?> createStudent(String name){
        try{
            Student student = new Student(name);
            studentRepository.save(student);

            return new ResponseEntity<>("Student Created", HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    //Read details of a student by ID
    public ResponseEntity<?> readStudentDetails(UUID id) {
        try {

            System.out.println("db");
            Student student = studentRepository.findByStudentId(id);

            return new ResponseEntity<>(student, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


}
