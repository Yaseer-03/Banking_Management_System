package com.example.BankingManagementSystem.Request;

import lombok.Data;

@Data
public class BankBranchRequest {

    private String ifscCode;
    private String branchCode;
    private String branchName;
    private String address;
    private String city;
    private String district;
    private String state;
    private String zipcode;    

}