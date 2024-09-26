package com.example.BankingManagementSystem.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                                    // Unique id to identify the unique row

    private LocalDateTime transactionDate;              // Date of transaction
    private Double amount;                              // Transaction amount
    private String transactionType;                     // Transaction type:  credit / debit

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;
    
}
