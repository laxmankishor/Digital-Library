package com.example.Digital_Library_Management_System.dto;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class SearchBookResponse {

    List<BookResponse> bookResponses;
    

}
