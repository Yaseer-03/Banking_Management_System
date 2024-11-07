// Address of the customer
package com.example.BankingManagementSystem.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class UserAddressDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                                   // Unique id to identify the unique row

    private String street;
    private String city;
    private String district;
    private String state;
    private Long zipcode;

    @OneToOne
    @JoinColumn(name ="user_id",referencedColumnName = "userId")
    private User user;
       
}
