package com.example.Student.StudentInfo.DTOs;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class StudentDTO {

    private String studentName;

    public String getStudentName() {
        return studentName;
    }
}
