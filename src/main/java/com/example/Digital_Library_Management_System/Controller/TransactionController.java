package com.example.Digital_Library_Management_System.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Digital_Library_Management_System.dto.InitiateTransactionRequest;
import com.example.Digital_Library_Management_System.service.TransactionService;

import jakarta.validation.Valid;

@RestController
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    public ResponseEntity<String> initiateTnx(@RequestBody @Valid InitiateTransactionRequest initiateTransactionRequest){

        try{
            String transactionId = transactionService.initiateTxn(initiateTransactionRequest);
            return ResponseEntity.ok("TransactionId : " + transactionId);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(e.getMessage());
        }
    }
    
}
