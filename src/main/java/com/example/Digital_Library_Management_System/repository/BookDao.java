package com.example.Digital_Library_Management_System.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Digital_Library_Management_System.model.Book;

@Repository
public interface BookDao extends JpaRepository<Book,Integer> {

}
