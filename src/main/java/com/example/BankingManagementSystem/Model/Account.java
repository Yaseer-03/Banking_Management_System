package com.example.BankingManagementSystem.Model;

import java.util.List;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                                            // Unique id to identify the unique row

    private String accountNumber;                               // Account number (Generate 11 - digit account number)
    private String accountType;                                 // Account type "savings/current"
    private LocalDateTime createdAt;                           // Date and time of the account creation

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "balance_id", referencedColumnName = "id")
    private Balance balance;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "branch_id", nullable = false)
    private Bank bankBranch;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<Transaction> transactions;
    
}
