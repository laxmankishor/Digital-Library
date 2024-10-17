package com.example.Digital_Library_Management_System.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Digital_Library_Management_System.dto.CreateStudentRequest;
import com.example.Digital_Library_Management_System.service.StudentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @PostMapping("/create")
    public ResponseEntity<String> createStudent(@RequestBody @Valid CreateStudentRequest createStudentRequest){

        studentService.createOrUpdate(createStudentRequest.to());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Student got created successfully");

    }


}
