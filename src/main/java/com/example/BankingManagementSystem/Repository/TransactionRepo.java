package com.example.BankingManagementSystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.BankingManagementSystem.Model.Transaction;

@Repository
public interface TransactionRepo extends JpaRepository<Transaction, Long>{
    
}
