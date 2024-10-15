package com.example.Digital_Library_Management_System.model;

import java.util.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Author {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Integer id;

     private String name;

     @Getter
    @Column(unique = true, nullable = false)
    private String email;

    @CreationTimestamp
    private Date createdOn;
    

    @OneToMany(mappedBy = "author")
    private List<Book> bookList;



    
}
