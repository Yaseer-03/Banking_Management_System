package com.example.BankingManagementSystem.Model;

import java.util.List;
import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;


@Entity
@Data
public class User {
        
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;                                // Unique id to identify the unique row
    private String firstName;                           // user's first name
    private String lastName;                            // user's last name                                        
    private String mobileNumber;                        // user's mobile number
    private String email;                               // user's email
    private String password;                            // Hashed password
    private LocalDate dateOfBirth;                      // Date of birth of the user
    private String role;                                // admin, customer
    private LocalDateTime createdAt;                    // Account creation timestamp
    private LocalDateTime updatedAt;                    // Last update timestamp
    private boolean isVerified;                         // Email/phone verification status

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private CustomerAddressDetails customerAddressDetails; // One user can have only one addressS

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Loan> loans;                           // One user can have many loans (Home loan, car loan ..etc)

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Account account;                             // One user can have only one account

    // pr check


}
