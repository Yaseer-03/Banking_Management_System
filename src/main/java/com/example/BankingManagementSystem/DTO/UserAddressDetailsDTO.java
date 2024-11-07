package com.example.BankingManagementSystem.Dto;

import lombok.Data;

@Data
public class UserAddressDetailsDTO {
    
    private Long id; 
    private String street;
    private String city;
    private String state;
    private Long zipcode;
   
}
