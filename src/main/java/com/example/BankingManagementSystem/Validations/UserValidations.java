package com.example.Validations;

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
    public Boolean isPasswordValid(String password) {
        // Password has to contain at least one uppercase letter and one special
        // character
        String passwordPattern = "^(?=.*[A-Z])(?=.*[!@#&()\\-\\[\\]{}:;',?/*~$^+=<>]).+$";
        return (password == null || password.length() < 8 || password.length() > 15) ? false
                : Pattern.matches(passwordPattern, password);
    }
 
    // * Validating email id
    public Boolean isEmailValid(String email){
        return email.contains("@");
    }

}
