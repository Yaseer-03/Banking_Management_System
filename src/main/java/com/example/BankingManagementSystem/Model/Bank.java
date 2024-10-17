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

    private String bankName;
    private String ifscCode;                                          // Branch code (IFSC code)

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "branch_address_id", referencedColumnName = "id")
    private BankBranchAddress bankBranchAddress;                        // Address of the bank branch

    @OneToMany(mappedBy = "bank", cascade = CascadeType.ALL)
    private List<Account> accounts;                                     // List of accounts in the bank branch
}
