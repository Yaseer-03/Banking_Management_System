// Address of the bank branch
package com.example.BankingManagementSystem.Model;

import java.util.List;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class BankBranchAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                                    // Unique id to identify the unique row
                       
    private String ifscCode;
    private String branchName;
    private String city;
    private String district;
    private String state;
    private String zipcode;                             
    
    @OneToMany(mappedBy = "bankBranchAddress", cascade = CascadeType.ALL)
    private List<Account> accounts;
    
}
