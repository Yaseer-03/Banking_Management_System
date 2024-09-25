package com.example.BankingManagementSystem.Model;

import java.util.List;
import java.time.LocalDateTime;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;
import java.time.LocalDate;


@Entity
@Data
public class User {
        
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;

    private String name;
    private String mobileNumber;
    private String email;
    private String password;            // Hashed password
    private LocalDate dateOfBirth;
    private String role;                // e.g., admin, customer
    private String accountStatus;       // e.g., active, suspended
    private LocalDateTime createdAt;    // Account creation timestamp
    private LocalDateTime updatedAt;    // Last update timestamp
    private boolean isVerified;         // Email/phone verification status

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private AddressCustomer addressCustomer;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Loan> loans;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Account> accounts;

}
