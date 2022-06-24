package com.example.Student.Interfaces;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface StudentInterface {


    ResponseEntity<?> createStudent(String name);

    ResponseEntity<?> readStudentDetails(UUID id);


}
