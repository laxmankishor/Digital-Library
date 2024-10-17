package com.example.Digital_Library_Management_System.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Digital_Library_Management_System.model.Author;
import com.example.Digital_Library_Management_System.model.Book;
import com.example.Digital_Library_Management_System.model.Genre;
import com.example.Digital_Library_Management_System.repository.BookDao;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

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

    public  List<Book> findBook(String searchKey, String searchValue) throws Exception{
        return switch (searchKey) {
          
            case "genre" -> bookDao.findByGenre(Genre.valueOf(searchValue));

            case "name" -> bookDao.findByName(searchValue);

            case "author_name" -> bookDao.findByAuthorName(searchValue);

            case "id" -> {
                Optional<Book> book = bookDao.findById(Integer.parseInt(searchValue));
                yield book.map(List::of).orElseGet(ArrayList::new);
            }
            default -> throw new Exception("Invalid search key: " + searchKey);
        };
        
    }

    
}
