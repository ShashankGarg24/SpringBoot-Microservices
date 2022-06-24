package com.example.Student.StudentInfo.Interfaces;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface StudentInterface {

    String updateStudentDetails(UUID id, String newName);

    String deleteStudent(UUID id);

    ResponseEntity<?> getAllStudentDetails();

}
