package com.example.BankingManagementSystem.Dto;

import java.time.LocalDateTime;
import lombok.Data;
@Data
public class BalanceDTO {
    
    private Double currentBalance;
    private String lastUpdatedAt;

}
