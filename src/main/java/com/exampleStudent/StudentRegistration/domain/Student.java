package com.exampleStudent.StudentRegistration.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Student {
	
	 @Id
	    @GeneratedValue(strategy= GenerationType.IDENTITY)
	    private Long id;
		private String studentname;
		private String course;
		private Double fee;
	    public Student() {
	    }
	    public Student(Long id, String studentname, String course, Double fee) {
	    
	        this.id = id;
	        this.studentname = studentname;
	        this.course = course;
	        this.fee = fee;
	    }
	    public Long getId() {
	        return id;
	    }
	    public void setId(Long id) {
	        this.id = id;
	    }
	    public String getStudentname() {
	        return studentname;
	    }
	    public void setStudentname(String studentname) {
	        this.studentname = studentname;
	    }
	    public String getCourse() {
	        return course;
	    }
	    public void setCourse(String course) {
	        this.course = course;
	    }
	    public Double getFee() {
	        return fee;
	    }
	    public void setFee(Double fee) {
	        this.fee = fee;
	    }

}
