package com.exampleStudent.StudentRegistration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exampleStudent.StudentRegistration.domain.AppUser;
import com.exampleStudent.StudentRegistration.repository.UserRepository;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<AppUser> register(@RequestBody AppUser user) {
        // Encrypt the password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Check if role is provided in the request, default to ROLE_STUDENT if not provided
        if (user.getRole() == null || (!user.getRole().equals("ROLE_ADMIN") && !user.getRole().equals("ROLE_STUDENT"))) {
            user.setRole("ROLE_STUDENT"); // Default to student role if no valid role is provided
        }

        // Save the user with the appropriate role
        AppUser savedUser = userRepo.save(user);
        return ResponseEntity.ok(savedUser);
    }
}

