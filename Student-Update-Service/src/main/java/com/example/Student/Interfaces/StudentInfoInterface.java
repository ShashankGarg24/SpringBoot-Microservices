package com.example.Student.Interfaces;

import com.example.Student.Entities.Student;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(url = "http://localhost:8080/api", value = "Student")
public interface StudentInfoInterface {

    //Get all students using OpenFeign
    @GetMapping(path = "/students-info")
    List<Student> getAllStudents();

}
