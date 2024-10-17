package com.example.BankingManagementSystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.BankingManagementSystem.Model.Bank;

@Repository
public interface BankRepo extends JpaRepository<Bank, Long> {

    Bank findByBankName(String string);
    
}
