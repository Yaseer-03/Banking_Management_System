package com.example.BankingManagementSystem.Validations;

import org.springframework.stereotype.Component;
import java.util.regex.Pattern;

@Component
public class UserValidations {

    // * Validating mobile number
    public Boolean isMobileNumberValid(String mobileNumber) {
        String mobileNumberPattern = "[6-9]{1}[0-9]{9}";
        return (mobileNumber == null) ? false : Pattern.matches(mobileNumberPattern, mobileNumber);
    }

    // * Validating password
    public Boolean isMpinValid(String mpin) {
        // Password has to contain at least one uppercase letter and one special
        // character
        String mpinPattern  = "[0-9]{4}";
        return (mpin == null || mpin.length() < 4 || mpin.length() > 6) ? false
                : Pattern.matches(mpinPattern, mpin);
    }
 
    // * Validating email id
    public Boolean isEmailValid(String email){
        return email.contains("@");
    }

}
