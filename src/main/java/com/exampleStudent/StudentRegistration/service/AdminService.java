package com.exampleStudent.StudentRegistration.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exampleStudent.StudentRegistration.domain.Admin;
import com.exampleStudent.StudentRegistration.domain.AppUser;
import com.exampleStudent.StudentRegistration.repository.AdminRepository;
import com.exampleStudent.StudentRegistration.repository.UserRepository;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private UserRepository UserRepository;

    // Create a new admin
    public Admin createAdmin(Admin admin) {
        AppUser appUser = admin.getAppUser();  // Admin must have an AppUser (authentication part)
        appUser.setRole("ROLE_ADMIN");  // Ensure role is "ROLE_ADMIN"
        UserRepository.save(appUser);  // Save the AppUser to the DB

        return adminRepository.save(admin);  // Save the Admin entity
    }

    // Get all admins
    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    // Find an admin by email
    public Admin getAdminByEmail(String email) {
        return adminRepository.findByEmail(email);
    }

    // Delete an admin
    public void deleteAdmin(Long adminId) {
        adminRepository.deleteById(adminId);
    }
}


