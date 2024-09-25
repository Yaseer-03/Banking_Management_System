package com.example.BankingManagementSystem.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class Loan {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double loanAmount;
    private Double interestRate;
    private String loanType;
    private String loanStatus;
    private LocalDate startDate;
    private LocalDate endDate;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
