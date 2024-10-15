package com.example.Digital_Library_Management_System.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Digital_Library_Management_System.model.Author;
import com.example.Digital_Library_Management_System.repository.AuthorDao;

@Service
public class AuthorService {

    @Autowired
    AuthorDao authorDao;


    //We dont need any API to create an Author, author would be added/created/updated as part of addition of new book
    public Author getOrCreate(Author author){
        Author existingAuthor = authorDao.findByEmail(author.getEmail());
        
        if(existingAuthor==null){
            existingAuthor = authorDao.save(author);
        }
        return existingAuthor;
    }
    
}
