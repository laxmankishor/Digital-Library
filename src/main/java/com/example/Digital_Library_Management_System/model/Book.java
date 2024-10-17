package com.example.Digital_Library_Management_System.model;

import java.util.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.example.Digital_Library_Management_System.dto.BookResponse;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter @Setter
public class Book {



     @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Getter @Setter
    @ManyToOne
    @JoinColumn
    private Author author;

    @Enumerated(value = EnumType.STRING)
    private Genre genre;

    private Integer price;

    @ManyToOne
    @JoinColumn
    private Student book_student;

    @CreationTimestamp
    private Date createdOn;


    @UpdateTimestamp
    private Date updatedOn;

    @OneToMany(mappedBy = "book")
    private List<Transaction> transaction;


    public BookResponse to(){
        return BookResponse.builder()
                .author(this.author)
                .book_student(this.book_student)
                .genre(this.genre)
                .id(this.id)
                .name(this.name)
                .price(this.price)
                .createdOn(this.createdOn)
                .updatedOn(this.updatedOn)
                .build();
    }



    
}
