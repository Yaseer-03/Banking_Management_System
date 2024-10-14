package com.example.BankingManagementSystem.Validations;

import org.springframework.stereotype.Component;
import com.example.BankingManagementSystem.Request.UserRequest;
import java.time.LocalDate;
import java.time.Period;
import java.util.regex.Pattern;

@Component
public class UserValidations {

    // * Null check for every field
    public Boolean isNull(String value) {
        return value == null || value.isEmpty();
    }

    // * Validating mobile number
    public Boolean isMobileNumberValid(String mobileNumber) {
        String mobileNumberPattern = "[6-9]{1}[0-9]{9}";
        return Pattern.matches(mobileNumberPattern, mobileNumber);
    }

    // * Validating Aadhaar number (12 digits)
    public Boolean isAadharValid(String aadharNumber) {
        String aadharPattern = "[0-9]{12}";
        return Pattern.matches(aadharPattern, aadharNumber);
    }

    // * Validating password (Mpin)
    public Boolean isMpinValid(String mpin) {
        String mpinPattern = "[0-9]{4}";
        return Pattern.matches(mpinPattern, mpin);
    }

    // * Validating email id
    public Boolean isEmailValid(String email) {
        String emailPattern = "^[A-Za-z0-9+_.-]+@(.+)$";
        return Pattern.matches(emailPattern, email);
    }

    // * Validating date of birth (Age should be 18+)
    public Boolean isDateOfBirthValid(LocalDate dateOfBirth) {
        if (dateOfBirth == null) return false;
        return Period.between(dateOfBirth, LocalDate.now()).getYears() >= 18;
    }

    // * Centralized validation method
    public String validateUserFields(UserRequest userRequest) {

        // * Null checks
        if (isNull(userRequest.getFirstName())) return "Please enter your first name";
        if (isNull(userRequest.getLastName())) return "Please enter your last name";
        if (isNull(userRequest.getMobileNumber())) return "Please enter your mobile number";
        if (isNull(userRequest.getAadharNumber())) return "Please enter your Aadhaar number";
        if (isNull(userRequest.getEmail())) return "Please enter your email";
        if (isNull(userRequest.getMpin())) return "Please set your Mpin";
        if (userRequest.getDateOfBirth() == null) return "Please enter your date of birth";

        // * Field validations
        if (!isMobileNumberValid(userRequest.getMobileNumber())) return "Please enter a valid mobile number";
        if (!isAadharValid(userRequest.getAadharNumber())) return "Please enter a valid Aadhaar number";
        if (!isEmailValid(userRequest.getEmail())) return "Please enter a valid email";
        if (!isMpinValid(userRequest.getMpin())) return "Please set a valid 4-digit Mpin";
        if (!isDateOfBirthValid(userRequest.getDateOfBirth())) return "You must be at least 18 years old to register";

        return null; // * No validation errors
    }
}
