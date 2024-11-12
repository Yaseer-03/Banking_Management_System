package com.example.BankingManagementSystem.Request;

import lombok.Data;

@Data
public class UserAddressDetailsRequest {

    private String street;
    private String city;
    private String district;
    private String state;
    private Long zipcode;
    
}
