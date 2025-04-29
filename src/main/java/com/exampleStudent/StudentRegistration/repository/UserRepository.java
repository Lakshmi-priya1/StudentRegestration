package com.exampleStudent.StudentRegistration.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exampleStudent.StudentRegistration.domain.AppUser;

 public interface UserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByUsername(String username);
}

