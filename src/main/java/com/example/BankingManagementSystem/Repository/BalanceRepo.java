package com.example.BankingManagementSystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.BankingManagementSystem.Model.Balance;

@Repository
public interface BalanceRepo extends JpaRepository <Balance, Long> {
    
}
