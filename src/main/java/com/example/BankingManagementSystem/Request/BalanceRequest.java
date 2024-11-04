package com.example.BankingManagementSystem.Request;

import lombok.Data;

@Data
public class BalanceRequest {
    private String accountNumber;
    private String pin;
}
