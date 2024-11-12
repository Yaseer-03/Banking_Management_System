// Bank entity which contains bank details
package com.example.BankingManagementSystem.Model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Data
public class Bank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                                                    // Unique id to identify the unique row

    private String bankName;                                     // Branch code (IFSC code)                       // Address of the bank branch

    @OneToMany(mappedBy = "bank", cascade = CascadeType.ALL)
    private List<Account> accounts;                                     // List of accounts in the bank branch
}
