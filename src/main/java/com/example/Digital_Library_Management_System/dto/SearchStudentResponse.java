package com.example.Digital_Library_Management_System.dto;

import java.util.Date;
import java.util.List;


import com.example.Digital_Library_Management_System.model.Book;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Builder
public class SearchStudentResponse {

     private Integer id;

 
    private String name;

    private String email;

    private String rollNumber;

    private Integer age;

    private Date createdOn;

    private Date updatedOn;

    private List<Book> bookList;

    private String errorMessage;

    public static SearchStudentResponse createErrorResponse(String errorMessage){
        return SearchStudentResponse.builder()
                    .errorMessage(errorMessage)
                    .build();
    }

    
}
