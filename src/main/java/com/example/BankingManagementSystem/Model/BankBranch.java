// Address of the bank branch
package com.example.BankingManagementSystem.Model;

import java.util.List;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class BankBranch{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                                    // Unique id to identify the unique row
                       
    @Column(unique = true, nullable = false)
    private String ifscCode;
    private String branchCode;
    private String branchName;
    private String address;
    private String city;
    private String district;
    private String state;
    private String zipcode;                             
    
    @OneToMany(mappedBy = "ifscCode", cascade = CascadeType.ALL)
    private List<Account> accounts;
    
}
