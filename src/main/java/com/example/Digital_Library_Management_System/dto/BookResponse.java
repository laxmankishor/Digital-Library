package com.example.Digital_Library_Management_System.dto;
import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.example.Digital_Library_Management_System.model.Author;
import com.example.Digital_Library_Management_System.model.Genre;
import com.example.Digital_Library_Management_System.model.Student;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Builder
public class BookResponse {

    private Integer id;

    private String name;

    private Author author;

    private Genre genre;

    private Integer price;

    private Student book_student;

    private Date createdOn;

    private Date updatedOn;
    
}
