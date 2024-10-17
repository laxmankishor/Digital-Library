package com.example.Digital_Library_Management_System.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Digital_Library_Management_System.dto.BookResponse;
import com.example.Digital_Library_Management_System.dto.CreateBookRequest;
import com.example.Digital_Library_Management_System.dto.SearchBookResponse;
import com.example.Digital_Library_Management_System.dto.SearchRequest;
import com.example.Digital_Library_Management_System.model.Book;
import com.example.Digital_Library_Management_System.service.BookService;
import java.util.List;
import java.util.ArrayList;
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


    // Get/fetch book based on BookName, Genre, Author and Id
    @GetMapping("/search")
    public SearchBookResponse getBook(@RequestBody @Valid SearchRequest searchRequest){

        try{
        List<Book> bookList = bookService.findBook(searchRequest.getSearchKey(), searchRequest.getSearchValue());
        List<BookResponse> bookResponsesList = new ArrayList<>();

        for(Book book : bookList){
            bookResponsesList.add(book.to());
        }

        return new SearchBookResponse(bookResponsesList);
        }
        catch(Exception e){
            return new SearchBookResponse();
        }



    }

    
}
