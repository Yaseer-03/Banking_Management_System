package com.example.BankingManagementSystem.Model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import com.example.BankingManagementSystem.Enums.*;


@Entity
@Data
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                                    // Unique id to identify the unique row

    private Long transactionId;                         // generate 12 digit random number (Business logic)
    private String initiatedBy;                         // User's name (Account holder's name) don't take from the user, fetch form db and save the user name 
    private LocalDateTime transactionDate;              // Date of transaction
    private Double amount;                              // Transaction amount
    private TransactionType transactionType;            // Transaction type: debit
    private TransactionStatus transactionStatus;        // Transaction status : Failed, completed // based on the balance  
    private ModeOfTransaction modeOfTransaction;        // Transaction mode : upi, card, online banking  
    private String transactionDescription;              // Transaction description: utility bill payment" or "fund transfer.
    private Long recipientAccountNumber;                // receiver's account number

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;
    
}
