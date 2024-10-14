package com.example.BankingManagementSystem.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.BankingManagementSystem.Model.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    Boolean existsByEmail(String email);
    Boolean existsByMobileNumber(String mobileNumber);
    Boolean existsByAadharNumber(String aadharNumber);
    Optional <User> findByMobileNumber(String mobileNumber);
    void deleteByMobileNumber(String mobileNumber);

}
