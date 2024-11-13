package com.example.BankingManagementSystem.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.BankingManagementSystem.Model.BankBranch;
@Repository
public interface BankBranchRepo extends JpaRepository<BankBranch, Long>{

    Optional<BankBranch> findByIfscCode(String ifscCode);
    
}