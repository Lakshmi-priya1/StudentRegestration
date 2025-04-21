package com.exampleStudent.StudentRegistration.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.exampleStudent.StudentRegistration.domain.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}


