package com.example.BankingManagementSystem.Model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
public class Balance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                                    // Unique id to identify the unique row

    private Double currentBalance;                      // Current balance 
    private LocalDateTime lastUpdatedAt;                // last updated time of the balance

    @OneToOne(mappedBy = "balance", cascade = CascadeType.ALL)
    private Account account;
    
}
