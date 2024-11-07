package com.example.BankingManagementSystem.Dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class AccountDTO {
    
    private String accountNumber;
    private String accountType;
    private LocalDateTime createdAt;
    private String accountStatus;

    private BankBranchAddressDTO bankBranchAddressDTO;

}
