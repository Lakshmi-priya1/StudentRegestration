package com.exampleStudent.StudentRegistration.controller;

import com.exampleStudent.StudentRegistration.domain.Student;
import com.exampleStudent.StudentRegistration.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students") // Base URL for API endpoints
public class StudentRestController {

    @Autowired
    private StudentService service;

    // Create new student (POST JSON)
    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Student savedStudent = service.save(student);
        return ResponseEntity.ok(savedStudent);
    }

    // Get all students
    @GetMapping
    public List<Student> getAllStudents() {
        return service.listAll();
    }

    // Get student by ID
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable long id) {
        try {
            Student student = service.get(id);
            return ResponseEntity.ok(student);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Update student by ID
    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable long id, @RequestBody Student updatedStudent) {
        try {
            Student existingStudent = service.get(id);
            existingStudent.setStudentname(updatedStudent.getStudentname());
            existingStudent.setCourse(updatedStudent.getCourse());
            existingStudent.setFee(updatedStudent.getFee());

            Student saved = service.save(existingStudent);
            return ResponseEntity.ok(saved);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete student
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable long id) {
        try {
            service.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}


