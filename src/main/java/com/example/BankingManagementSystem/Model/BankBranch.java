package com.example.BankingManagementSystem.Model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.*;

@Entity
@Data
public class BankBranch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String branchName;
    private String branchCode;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_branch_id", referencedColumnName = "id")
    private AddressBranch addressBranch;

    @OneToMany(mappedBy = "bankBranch", cascade = CascadeType.ALL)
    private List<Account> accounts;
    
}
