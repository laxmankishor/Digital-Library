package com.example.Digital_Library_Management_System.dto;

import com.example.Digital_Library_Management_System.model.TransactionType;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class InitiateTransactionRequest {

    //AdminId, Student RollNumber, BookId, TransactionType : these fields should be there while initiating any transaction

    @NotNull
    private Integer adminId;
    @NotBlank
    private String studentRollNumber;
    @NotNull
    private Integer bookId;
    @NotBlank
    private TransactionType transactionType;
    
}
