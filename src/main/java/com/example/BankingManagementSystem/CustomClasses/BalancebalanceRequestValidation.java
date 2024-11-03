package com.example.BankingManagementSystem.CustomClasses;

import com.example.BankingManagementSystem.Request.BalanceRequest;

public class BalancebalanceRequestValidation {

    // * Null check
    public Boolean isNullOrEmpty(String value) {
        return value == null || value.isEmpty();
    }

    public String validateBalancebalanceRequest(BalanceRequest balanceRequest) {
        if (balanceRequest == null) {
            return "Enter Account Number and Pin ";
        }

       // Validate account number
       if (isNullOrEmpty(balanceRequest.getAccountNumber())) {
        return "Account number is null or empty";
    } else if (balanceRequest.getAccountNumber().length() != 11) {
        return "Enter a valid Account Number (11 digits)";
    }

    // Validate pin
    if (isNullOrEmpty(balanceRequest.getPin())) {
        return "Pin is null or empty";
    } else if (balanceRequest.getPin().length() != 4 || !balanceRequest.getPin().matches("\\d{4}")) {
        return "Enter a valid Pin (4 digits)";
    }

    return null;
    }
}
