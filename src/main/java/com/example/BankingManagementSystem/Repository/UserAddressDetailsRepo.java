package com.example.BankingManagementSystem.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.BankingManagementSystem.Model.UserAddressDetails;

@Repository
public interface UserAddressDetailsRepo extends JpaRepository<UserAddressDetails, Long> {
    Optional<UserAddressDetails> findByUser_UserId(Long userId);
    
}
