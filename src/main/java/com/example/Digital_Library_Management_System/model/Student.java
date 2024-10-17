package com.example.Digital_Library_Management_System.model;

import java.util.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.example.Digital_Library_Management_System.dto.SearchStudentResponse;
import com.example.Digital_Library_Management_System.service.StudentService;

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
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student {


     @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Integer id;

    @Getter @Setter
    private String name;

    @Getter @Setter
    @Column(unique = true, nullable = false)
    private String email;

    @Getter @Setter
    @Column(unique = true, nullable = false)
    private String rollNumber;

    @Getter @Setter
    private Integer age;

    @CreationTimestamp
    private Date createdOn;

    @UpdateTimestamp
    private Date updatedOn;
    

    @OneToMany(mappedBy = "student")
    private List<Transaction> transaction;

    @OneToMany(mappedBy = "book_student")
    private List<Book> bookList;


    public SearchStudentResponse to(){
        return SearchStudentResponse.builder()
                    .name(this.name)
                    .email(this.email)
                    .id(this.id)
                    .rollNumber(this.rollNumber)
                    .age(this.age)
                    .createdOn(this.createdOn)
                    .updatedOn(this.updatedOn)
                    .bookList(this.bookList)
                    .build();

    }
    
}
