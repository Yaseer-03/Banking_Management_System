// Loan entity which contains details
package com.example.BankingManagementSystem.Model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
public class Loan {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                                    // Unique id to identify the row uniquely

    private Double loanAmount;                         // Loan amount
    private Double interestRate;                       // Interest rate based on the type of loan
    private String loanType;                           // Home loans, car loan ...etc
    private String loanStatus;                         // Approved, rejected
    private LocalDate issuedDate;                      // Loan issued date 
    private LocalDate endDate;                         // Loan repayment date 

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
