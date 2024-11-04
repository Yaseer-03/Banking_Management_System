package com.example.BankingManagementSystem.DTO;

import java.time.LocalDateTime;
import lombok.Data;
@Data
public class BalanceDTO {
    
    private Double currentBalance;
    private LocalDateTime lastUpdatedAt;

}
