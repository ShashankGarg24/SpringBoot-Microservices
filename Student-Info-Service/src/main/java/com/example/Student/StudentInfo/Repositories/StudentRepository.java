package com.example.Student.StudentInfo.Repositories;

import com.example.Student.StudentInfo.Entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.UUID;

@Repository
public interface StudentRepository extends JpaRepository<Student, UUID> {

    Student findByStudentId(UUID id);

    @Transactional
    @Modifying
    @Query("delete from Student s where s.studentId= ?1")
    void deleteStudent(UUID id);

}
