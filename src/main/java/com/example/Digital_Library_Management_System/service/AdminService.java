package com.example.Digital_Library_Management_System.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Digital_Library_Management_System.model.Admin;
import com.example.Digital_Library_Management_System.repository.AdminDao;

@Service
public class AdminService {


    @Autowired
    AdminDao adminDao;

    public void createAdmin(Admin admin){
        adminDao.save(admin);
        
    }

    public Admin findAdmin(Integer adminId) {
        return adminDao.findById(adminId).orElse(null);
    }

    
}
