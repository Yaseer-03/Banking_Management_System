package com.example.BankingManagementSystem.CustomClasses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.example.BankingManagementSystem.Repository.UserRepo;
import com.example.BankingManagementSystem.Request.MpinRequest;
import com.example.BankingManagementSystem.Request.UserAddressDetailsRequest;
import com.example.BankingManagementSystem.Request.UserRequest;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

@Component
public class UserValidations {

    @Autowired
    private UserRepo userRepo;

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

    public LocalDate isDateOfBirthValid(String dateOfBirthInput) {

        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate dateOfBirth;

        try {
            // Parse the date
            dateOfBirth = LocalDate.parse(dateOfBirthInput, inputFormatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Date of birth must be in DD-MM-YYYY format."); // Return error for
                                                                                               // invalid format
        }

        // Additional check for valid day in the month
        if (dateOfBirth.getDayOfMonth() != Integer.parseInt(dateOfBirthInput.substring(0, 2))) {
            throw new IllegalArgumentException(
                    "Invalid date: " + dateOfBirthInput + ". Please check the day and month.");
        }

        // Validate age
        if (Period.between(dateOfBirth, LocalDate.now()).getYears() < 18) {
            throw new IllegalArgumentException("You must be at least 18 years old to register.");
        }

        return dateOfBirth; // No validation errors
    }

    // * Centralized validation method
    public String validateUserFields(UserRequest userRequest) {

        // * Null checks && Validations && Duplication check
        if (isNull(userRequest.getFirstName()))
            return "Please enter your first name";
        if (isNull(userRequest.getLastName()))
            return "Please enter your last name";
        if (isNull(userRequest.getMobileNumber()))
            return "Please enter your mobile number";
        if (!isMobileNumberValid(userRequest.getMobileNumber()))
            return "Please enter a valid mobile number";
        if (userRepo.existsByMobileNumber(userRequest.getMobileNumber()))
            return "Mobile number is already in use..!";
        if (isNull(userRequest.getAadharNumber()))
            return "Please enter your Aadhaar number";
        if (!isAadharValid(userRequest.getAadharNumber()))
            return "Please enter a valid Aadhaar number";
        if (userRepo.existsByAadharNumber(userRequest.getAadharNumber()))
            return "Aadhar number is already in use..!";
        if (isNull(userRequest.getEmail()))
            return "Please enter your email";
        if (!isEmailValid(userRequest.getEmail()))
            return "Please enter a valid email";
        if (userRepo.existsByEmail(userRequest.getEmail()))
            return "Email already in use..!";
        if (isNull(userRequest.getDateOfBirth()))
            return "Please enter your date of birth";
            
        return null; // * No validation errors
    }

    // * Centralized validation method for ( Updating API )
    public String validatingUserFields(UserRequest userRequest, String currentMobileNumber, String currentAadharNumber,
            String currentEmail) {

        // * Null checks && Validations && Duplication check
        if (isNull(userRequest.getFirstName()))
            return "Please enter your first name";
        if (isNull(userRequest.getLastName()))
            return "Please enter your last name";
        if (isNull(userRequest.getMobileNumber()))
            return "Please enter your mobile number";
        if (!isMobileNumberValid(userRequest.getMobileNumber()))
            return "Please enter a valid mobile number";
        if (!userRequest.getMobileNumber().equals(currentMobileNumber)
                && userRepo.existsByMobileNumber(userRequest.getMobileNumber()))
            return "Mobile number is already in use..!";
        if (isNull(userRequest.getAadharNumber()))
            return "Please enter your Aadhaar number";
        if (!isAadharValid(userRequest.getAadharNumber()))
            return "Please enter a valid Aadhaar number";
        if (!userRequest.getAadharNumber().equals(currentAadharNumber)
                && userRepo.existsByAadharNumber(userRequest.getAadharNumber()))
            return "Aadhar number is already in use..!";
        if (isNull(userRequest.getEmail()))
            return "Please enter your email";
        if (!isEmailValid(userRequest.getEmail()))
            return "Please enter a valid email";
        if (!userRequest.getEmail().equals(currentEmail) && userRepo.existsByEmail(userRequest.getEmail()))
            return "Email already in use..!";
        if (isNull(userRequest.getDateOfBirth()))
            return "Please enter your date of birth";

        return null; // * No validation errors
    }

    // * Mpin validation
    public String mpinValidation(MpinRequest mpinRequest) {
        if (isNull(mpinRequest.getMpin()))
            return "Please set your Mpin";
        if (!isMpinValid(mpinRequest.getMpin()))
            return "Please set a valid 4-digit Mpin";
        return null;
    }

    // * Validating pincode

    public String zipCodeValidation(String zipcode) {
        if (zipcode.length() != 6) {
            return "Please enter valid zipcode";
        }
        return null;
    }

    // * User address null checks
    // ! TODO: Add validation for every property based on the datatype
    public String nullCheckForUserAddress(UserAddressDetailsRequest userAddressDetailsRequest) {
        if (isNull(userAddressDetailsRequest.getStreet()))
            return "Please enter street name!";
        if (isNull(userAddressDetailsRequest.getCity()))
            return "Please enter your city!";
        if (isNull(userAddressDetailsRequest.getState()))
            return "please enter your state!";
        if (isNull(String.valueOf(userAddressDetailsRequest.getZipcode())))
            return "Please enter your zipcode!";
        // Validate the pincode
        String pinValidationResult = zipCodeValidation(String.valueOf(userAddressDetailsRequest.getZipcode()));
        if (pinValidationResult != null) {
            return pinValidationResult;
        }
        return null;
    }
}
