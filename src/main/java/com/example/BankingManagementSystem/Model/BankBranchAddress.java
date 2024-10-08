// Address of the bank branch
package com.example.BankingManagementSystem.Model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class BankBranchAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                                    // Unique id to identify the unique row

    private String street;                             
    private String city;
    private String state;
    private String zipcode;                             
    
    @OneToOne(mappedBy = "bankBranchAddress", cascade = CascadeType.ALL)
    private Bank bankBranch;
    
}
