package com.example.Digital_Library_Management_System.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class SearchRequest {

    private String searchKey;
    
    private String searchValue;
    
}
