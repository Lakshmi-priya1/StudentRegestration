package com.exampleStudent.StudentRegistration.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping; // Added PUT
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exampleStudent.StudentRegistration.domain.Student;
import com.exampleStudent.StudentRegistration.service.StudentService;

@RestController
@RequestMapping("/api/students")
public class StudentRestController {

    @Autowired
    private StudentService service;

    // Get all students (Only accessible to Admins)
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')") // Only admins can access
    public List<Student> getAllStudents() {
        return service.listAll();
    }

    // Get a single student by ID (Accessible to Admins or the student themselves)
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable long id) {
        try {
            Student student = service.get(id);
            return ResponseEntity.ok(student);
        } catch (Exception e) {
            return ResponseEntity.status(403).body(null);
        }
    }

    // Create or update a student (Admins and the current student can save)
    @PostMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('STUDENT')") // Both can create or update their data
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Student savedStudent = service.save(student);
        return ResponseEntity.ok(savedStudent);
    }

    // Update student data (PUT request)
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('STUDENT')") // Only admins and the current student can update
    public ResponseEntity<Student> updateStudent(@PathVariable long id, @RequestBody Student studentDetails) {
        try {
            // Update the student and return the updated data
            Student updatedStudent = service.updateStudent(id, studentDetails);
            return ResponseEntity.ok(updatedStudent);
        } catch (Exception e) {
            // If the update fails or the user is not authorized, return a 403 Forbidden status
            return ResponseEntity.status(403).body(null);
        }
    }

    // Delete a student (Admins and the current student can delete)
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('STUDENT')") // Both can delete their data
    public ResponseEntity<Void> deleteStudent(@PathVariable long id) {
        try {
            service.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(403).build();
        }
    }
}






