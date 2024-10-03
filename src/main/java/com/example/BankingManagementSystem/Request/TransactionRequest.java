package com.example.BankingManagementSystem.Request;

import com.example.Enums.ModeOfTransaction;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class TransactionRequest {

    private Long recipientAccountNumber;
    private double amount;
    @Enumerated(EnumType.STRING)
    private ModeOfTransaction modeOfTransaction;
    private String transactionDescription;
    private Long pin;
 
}
