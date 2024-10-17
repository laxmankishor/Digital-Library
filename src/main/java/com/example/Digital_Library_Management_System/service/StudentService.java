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

    public Student findStudent(String searchKey, String searchValue) throws Exception {
        
        return switch(searchKey){
            case "rollNumber" -> studentDao.findByRollNumber(searchValue);

            case "email" -> studentDao.findByEmail(searchValue);

            case "id" -> studentDao.findById(Integer.parseInt(searchValue)).orElse(new Student());

            default -> throw new Exception("Search key is not valid: " + searchKey);

        };
    }





    
}
