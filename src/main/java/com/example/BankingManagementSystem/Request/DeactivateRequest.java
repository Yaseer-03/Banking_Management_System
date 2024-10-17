package com.example.BankingManagementSystem.Request;

import lombok.Data;

@Data
public class DeactivateRequest {
    private String accountNumber;
    private String accountType;
    private String pin;
}
