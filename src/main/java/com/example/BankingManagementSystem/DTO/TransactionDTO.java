package com.example.BankingManagementSystem.DTO;

import java.time.LocalDateTime;

import com.example.Enums.*;

public class TransactionDTO {
    
    private Long transactionId;                         
    private String initiatedBy;                          
    private LocalDateTime transactionDate;              
    private Double amount;                              
    private TransactionType transactionType;            
    private TransactionStatus transactionStatus;
    private ModeOfTransaction modeOfTransaction;
    private String transactionDescription;      
    private Long recipientAccountNumber;              

}
