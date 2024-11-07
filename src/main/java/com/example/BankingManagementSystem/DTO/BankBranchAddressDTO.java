package com.example.BankingManagementSystem.Dto;

import lombok.Data;

@Data
public class BankBranchAddressDTO {
    
    private Long id;
    private String branchName;
    private String street;
    private String city;
    private String state;
    private String zipcode;

}
