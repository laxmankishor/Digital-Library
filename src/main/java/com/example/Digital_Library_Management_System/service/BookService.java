package com.example.Digital_Library_Management_System.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Digital_Library_Management_System.model.Author;
import com.example.Digital_Library_Management_System.model.Book;
import com.example.Digital_Library_Management_System.repository.BookDao;


@Service
public class BookService {


    @Autowired
    BookDao bookDao;

    @Autowired
    AuthorService authorService;

    public void createOrUpdateBook(Book book){
        Author existingAuthor = authorService.getOrCreate(book.getAuthor());

        book.setAuthor(existingAuthor);
        bookDao.save(book);

    }


    
}
