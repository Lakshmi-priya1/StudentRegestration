package com.exampleStudent.StudentRegistration.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exampleStudent.StudentRegistration.domain.Student;
import com.exampleStudent.StudentRegistration.repository.StudentRepository;

@Service
public class StudentService {
	
	 @Autowired
	    private StudentRepository repo;
	    
	    public List<Student> listAll() {
	        return repo.findAll();
	    }
	     
	    public Student save(Student std) {
	        return repo.save(std);
	    }
	     
	    public Student get(long id) {
	        return repo.findById(id).get();
	    }
	     
	    public void delete(long id) {
	        repo.deleteById(id);
	    }

}
