package com.example.Student.StudentInfo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class StudentInfoApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentInfoApplication.class, args);
	}

}
