package com.example.BankingManagementSystem.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class AddressBranch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String street;
    private String city;
    private String state;
    private String zipcode;
    private String country;

    @OneToOne(mappedBy = "addressBranch")
    private BankBranch bankBranch;

    
}
