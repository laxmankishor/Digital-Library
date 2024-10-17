package com.example.Digital_Library_Management_System.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Digital_Library_Management_System.model.Student;
import com.example.Digital_Library_Management_System.repository.StudentDao;


@Service
public class StudentService {

    @Autowired
    StudentDao studentDao;

    public void createOrUpdate(Student student) {
        studentDao.save(student);
    }


    
}
