package com.example.BankingManagementSystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.BankingManagementSystem.Model.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    Boolean existsByEmail(String email);

    User findByMobileNumber();

}
