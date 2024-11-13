package com.example.BankingManagementSystem.Dto;

import lombok.Data;

@Data
public class AccountDTO {
    
    private String accountNumber;
    private String accountType;
    private String createdAt;
    private String accountStatus;

    private BankBranchDTO bankBranchDetails;

}
