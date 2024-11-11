package com.example.BankingManagementSystem.Request;

import lombok.Data;

@Data
public class UserRequest {

    private String firstName; 
    private String lastName; 
    private String mobileNumber;
    private String aadharNumber;
    private String email; 
    private String dateOfBirth;
    
}
