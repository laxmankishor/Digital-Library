package com.example.Digital_Library_Management_System.dto;



import com.example.Digital_Library_Management_System.model.Student;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class CreateStudentRequest {

    @NotBlank
    private String name;
    @NotBlank
    private String email;
    @NotBlank
    private String rollNumber;

    private Integer age;

    public Student to(){
        return Student.builder()
                .name(this.name)
                .email(this.email)
                .rollNumber(this.rollNumber)
                .age(this.age)
                .build();
    }
    
}
