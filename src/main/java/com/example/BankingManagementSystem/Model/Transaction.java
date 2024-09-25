package com.example.BankingManagementSystem.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime transactionDate;            
    private Double amount;
    private String transactionType;                 

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;
    
}
