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
    private Long id;

    private String accountNumber;
    private String accountType;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "balance_id", referencedColumnName = "id")
    private Balance balance;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "branch_id", nullable = false)
    private BankBranch bankBranch;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<Transaction> transactions;
    
}
