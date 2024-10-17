package com.example.Digital_Library_Management_System.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.Digital_Library_Management_System.model.Book;
import com.example.Digital_Library_Management_System.model.Genre;

@Repository
public interface BookDao extends JpaRepository<Book,Integer> {

    List<Book> findByName(String bookName);

    List<Book> findByGenre(Genre genre);


    @Query("Select b from Book b, Author a where b.author.id = a.id and a.name = ?1")
    List<Book> findByAuthorName(String authorName);

}
