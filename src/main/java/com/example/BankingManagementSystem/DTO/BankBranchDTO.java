package com.example.BankingManagementSystem.Dto;

import lombok.Data;

@Data
public class BankBranchDTO {
    
    private Long id;
    private String ifscCode;
    private String branchCode;
    private String branchName;
    private String address;
    private String city;
    private String district;
    private String state;
    private String zipcode;
}
