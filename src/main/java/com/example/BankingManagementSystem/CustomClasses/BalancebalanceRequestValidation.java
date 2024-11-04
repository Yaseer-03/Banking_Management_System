package com.example.BankingManagementSystem.CustomClasses;

import org.springframework.stereotype.Component;

import com.example.BankingManagementSystem.Request.BalanceRequest;
@Component
public class BalancebalanceRequestValidation {

    // * Null check
    public Boolean isNullOrEmpty(String value) {
        return value == null || value.isEmpty();
    }

    public String validateBalancebalanceRequest(BalanceRequest balanceRequest) {
        if (isNullOrEmpty(balanceRequest.getAccountNumber()) && isNullOrEmpty(balanceRequest.getPin())) {
            return "Enter Account Number and Pin";
        }

       // Validate account number
       if (isNullOrEmpty(balanceRequest.getAccountNumber())) {
        return "Enter Account Number...";
    } else if (balanceRequest.getAccountNumber().length() != 11) {
        return "Enter a valid Account Number (11 digits)";
    }

    // Validate pin
    if (isNullOrEmpty(balanceRequest.getPin()) ||balanceRequest.getPin().trim().isEmpty()) {
        return "Enter Pin...";
    } else if (balanceRequest.getPin().length() != 4 || !balanceRequest.getPin().matches("\\d{4}")) {
        return "Enter a valid Pin (4 digits)";
    }

    return null;
    }
}
