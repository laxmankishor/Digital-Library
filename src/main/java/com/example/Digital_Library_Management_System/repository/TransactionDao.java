package com.example.Digital_Library_Management_System.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.Digital_Library_Management_System.model.Transaction;
import com.example.Digital_Library_Management_System.model.TransactionType;

public interface TransactionDao extends JpaRepository<Transaction, Integer> {

@Query("SELECT t FROM Transaction t WHERE t.book.id = :bookId AND t.student.id = :studentId AND t.transactionType = :transactionType ORDER BY t.transactionId ASC")
public Transaction findBYStudentAndBookAndTransactionTypeOrderByIdAsc(@Param("studentId") Integer studentId, @Param("bookId") Integer bookId, @Param("transactionType") TransactionType transactionType);
}
