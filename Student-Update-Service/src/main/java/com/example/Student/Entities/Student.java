package com.example.Student.Entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.UUID;

@Entity
public class Student implements Serializable {

    @Id
    @Column(nullable = false, length = 16, unique = true)
    private UUID studentId;
    @Column(nullable = false)
    private String studentName;

    public Student() {
    }

    public Student(String studentName) {
        this.studentId = UUID.randomUUID();
        System.out.println(this.studentId);
        this.studentName = studentName;
    }

    public UUID getStudentId() {
        return studentId;
    }

    public void setStudentId(UUID studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
}
