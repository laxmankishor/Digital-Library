package com.example.Digital_Library_Management_System.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Digital_Library_Management_System.model.Author;

public interface AuthorDao extends JpaRepository<Author,Integer> {

    Author findByEmail(String email);
    
}
