package com.example.BankingManagementSystem.CustomClasses;

import java.util.regex.Pattern;
import org.springframework.stereotype.Component;
import com.example.BankingManagementSystem.Request.AccountRequest;

@Component
public class AccountValidation {
    // * Null check for every field
    public Boolean isNull(String value) {
        return value == null || value.isEmpty();
    }

    // * Validating password (pin)
    public Boolean isMpinValid(String pin) {
        String mpinPattern = "[0-9]{4}";
        return Pattern.matches(mpinPattern, pin);
    }


  // * Pin validation
public String validatePin(AccountRequest accountRequest) {
    // Check if accountRequest is null
    if (accountRequest == null) {
        return "Account details cannot be null";
    }

    // Check if the pin is null or empty
    if (isNull(accountRequest.getPin())) {
        return "Please set your Mpin";
    }

    // Check if the pin is a valid 4-digit number
    if (!isMpinValid(accountRequest.getPin())) {
        return "Please set a valid 4-digit pin";
    }

    return null; // All validations passed
}

}
