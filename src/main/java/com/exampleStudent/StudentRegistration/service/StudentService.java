package com.exampleStudent.StudentRegistration.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import com.exampleStudent.StudentRegistration.domain.Student;
import com.exampleStudent.StudentRegistration.repository.StudentRepository;

@Service
public class StudentService {

    @Autowired
    private StudentRepository repo;

    // Get all students (Only accessible to Admins)
    public List<Student> listAll() {
        if (isAdmin()) {
            return repo.findAll();
        } else {
            throw new SecurityException("You are not authorized to view all students.");
        }
    }

    // Save student (Admins and students can save their own data)
    public Student save(Student std) {
        if (isAdmin()) {
            return repo.save(std);
        } else {
            // Check if the user is trying to save their own data (e.g., current student)
            if (isCurrentStudent(std)) {
                return repo.save(std);
            } else {
                throw new SecurityException("You are not authorized to save other students' data.");
            }
        }
    }

    // Get student by ID (Only the student or an admin can get the data)
    public Student get(long id) {
        if (isAdmin() || isCurrentStudent(id)) {
            Optional<Student> student = repo.findById(id);
            return student.orElseThrow(() -> new IllegalArgumentException("Student not found"));
        } else {
            throw new SecurityException("You are not authorized to view this student's data.");
        }
    }

    // Delete student (Admins can delete anyone's data, but students can only delete their own)
    public void delete(long id) {
        if (isAdmin() || isCurrentStudent(id)) {
            repo.deleteById(id);
        } else {
            throw new SecurityException("You are not authorized to delete this student's data.");
        }
    }

    // Update student (Admins and current students can update their data)
    public Student updateStudent(long id, Student studentDetails) {
        if (isAdmin() || isCurrentStudent(id)) {
            Optional<Student> existingStudentOpt = repo.findById(id);
            if (existingStudentOpt.isPresent()) {
                Student existingStudent = existingStudentOpt.get();
                // Update fields with the new data from studentDetails
                existingStudent.setStudentname(studentDetails.getStudentname());
                existingStudent.setCourse(studentDetails.getCourse());
                existingStudent.setFee(studentDetails.getFee());
                return repo.save(existingStudent);
            } else {
                throw new IllegalArgumentException("Student not found");
            }
        } else {
            throw new SecurityException("You are not authorized to update this student's data.");
        }
    }

    // Helper method to check if the current user is an admin
    private boolean isAdmin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getAuthorities().stream().anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"));
    }

    // Helper method to check if the current user is the same as the student being accessed
    private boolean isCurrentStudent(long studentId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        return user.getUsername().equals(String.valueOf(studentId));
    }

    // Overloaded helper method for Student object
    private boolean isCurrentStudent(Student student) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        return user.getUsername().equals(String.valueOf(student.getId()));
    }
}



