package com.example.Digital_Library_Management_System.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Digital_Library_Management_System.dto.CreateBookRequest;
import com.example.Digital_Library_Management_System.service.BookService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookService bookService;

    @PostMapping("/save")
    public ResponseEntity<String> createOrUpdateBook(@RequestBody @Valid CreateBookRequest createBookRequest){
        
        bookService.createOrUpdateBook(createBookRequest.to());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Book got created successfully");
    }
    
}
