package com.example.Digital_Library_Management_System.dto;

import com.example.Digital_Library_Management_System.model.Admin;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreateAdminRequest {


    @NotBlank
    private String name;

    @NotBlank
    private String email;



    public Admin to(){
        return Admin.builder()
                .name(this.name)
                .email(this.email)
                .build();
    }

}
