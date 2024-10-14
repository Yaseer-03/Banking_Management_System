package com.example.BankingManagementSystem.Request;

import lombok.Data;

@Data
public class LoanRequest {
    
    private Double loanAmount;
    private String loanType;

}
