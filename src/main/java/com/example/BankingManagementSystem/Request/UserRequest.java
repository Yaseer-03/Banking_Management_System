package com.example.BankingManagementSystem.Request;

import java.time.LocalDate;
import lombok.Data;

@Data
public class UserRequest {

    private String firstName; 
    private String lastName; 
    private String mobileNumber;
    private String aadharNumber;
    private String email; 
    private LocalDate dateOfBirth;
    private String mpin;

}
