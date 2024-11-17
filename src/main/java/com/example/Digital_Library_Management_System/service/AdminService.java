package com.example.Digital_Library_Management_System.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Digital_Library_Management_System.Constants.Constants;
import com.example.Digital_Library_Management_System.model.Admin;
import com.example.Digital_Library_Management_System.model.User;
import com.example.Digital_Library_Management_System.repository.AdminDao;

@Service
public class AdminService {


    @Autowired
    AdminDao adminDao;

    @Autowired
    UserService userService;

    public void createAdmin(Admin admin) throws Exception{

        User user = admin.getUser();
        user = userService.save(Constants.ADMIN_USER, user);

        if(user.getId() == null) {
            throw new Exception("Invalid User");
            // TODO - handle exception
        }
        
        admin.setUser(user);
        adminDao.save(admin);
        
    }

    public Admin findAdmin(Integer adminId) {
        return adminDao.findById(adminId).orElse(null);
    }

    
}
