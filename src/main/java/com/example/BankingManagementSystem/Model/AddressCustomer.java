package com.example.BankingManagementSystem.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class AddressCustomer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String street;
    private String city;
    private String state;
    private String zipcode;
    private String country;

    @OneToOne(mappedBy = "addressCustomer")
    private User user;

    
}
