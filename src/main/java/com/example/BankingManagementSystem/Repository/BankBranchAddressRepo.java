package com.example.BankingManagementSystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.BankingManagementSystem.Model.BankBranchAddress;
@Repository
public interface BankBranchAddressRepo extends JpaRepository<BankBranchAddress, Long>{
    
}