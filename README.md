# SpringBoot-Task


Services used:

Eureka
Resilience4j
Spring API Gateway
Spring Cache
OpenFeign
Web


Available APIs

To fetch all students(GET)
http://localhost:8080/api/getAll   (OpenFeign)
http://localhost:8080/api/students-info

To create a student(POST)
http://localhost:8080/api/student

To fetch a student(GET)
http://localhost:8080/api/student/{id}

To update a student(PUT)
http://localhost:8080/api/student/{id}

To delete a student(DELETE)
http://localhost:8080/api/student/{id}

To get response from given URL using WebClient(POST)
http://localhost:8081/api/response
