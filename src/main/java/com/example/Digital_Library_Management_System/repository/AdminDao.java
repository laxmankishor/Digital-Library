package com.example.Digital_Library_Management_System.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Digital_Library_Management_System.model.Admin;

public interface AdminDao extends JpaRepository<Admin,Integer> {
    
}
