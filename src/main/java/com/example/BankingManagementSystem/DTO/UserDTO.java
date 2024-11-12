package com.example.BankingManagementSystem.Dto;
import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long userId;
    private String firstName;
    private String lastName;
    private String mobileNumber;
    private String email;
    private String aadharNumber;
    private String dateOfBirth;
    private String createdAt;
    private String updatedAt;

    private UserAddressDetailsDTO userAddressDetails;
    
}
