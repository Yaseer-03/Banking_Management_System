package com.example.BankingManagementSystem.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class Balance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double currentBalance;
    private LocalDateTime lastUpdatedAt;

    @OneToOne(mappedBy = "balance")
    private Account account;
    
}
