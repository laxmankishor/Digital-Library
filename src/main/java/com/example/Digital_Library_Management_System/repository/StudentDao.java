package com.example.Digital_Library_Management_System.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Digital_Library_Management_System.model.Student;


@Repository
public interface StudentDao extends JpaRepository<Student,Integer> {
    
}
