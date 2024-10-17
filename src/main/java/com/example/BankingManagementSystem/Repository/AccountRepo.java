package com.example.BankingManagementSystem.Repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.BankingManagementSystem.Model.Account;
import com.example.BankingManagementSystem.Model.User;

@Repository
public interface AccountRepo extends JpaRepository<Account, Long> {

    Optional<Account> findByUser_UserId(Long userId);

    Optional<Account> findByaccountNumber(String accountNumber);
    
}
