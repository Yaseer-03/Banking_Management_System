package com.example.BankingManagementSystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.BankingManagementSystem.Model.Loan;

@Repository
public interface LoanRepo extends JpaRepository<Loan, Long> {

}
