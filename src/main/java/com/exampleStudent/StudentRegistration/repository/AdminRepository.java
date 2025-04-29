package com.exampleStudent.StudentRegistration.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.exampleStudent.StudentRegistration.domain.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    // You can add custom queries here if needed, such as finding admins by their email, etc.
    Admin findByEmail(String email);
}

