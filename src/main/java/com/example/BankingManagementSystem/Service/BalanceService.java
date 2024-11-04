package com.example.BankingManagementSystem.Service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.BankingManagementSystem.CustomClasses.BalanceRequestValidation;
import com.example.BankingManagementSystem.DTO.BalanceDTO;
import com.example.BankingManagementSystem.DTO.ResponseWrapper;
import com.example.BankingManagementSystem.Model.Account;
import com.example.BankingManagementSystem.Model.Balance;
import com.example.BankingManagementSystem.Repository.AccountRepo;
import com.example.BankingManagementSystem.Repository.BalanceRepo;
import com.example.BankingManagementSystem.Request.BalanceRequest;

@Service
public class BalanceService {

    @Autowired
    BalanceRequestValidation balanceRequestValidation;
    @Autowired
    AccountRepo accountRepo;

    @Autowired
    BalanceRepo balanceRepo;
    
    public ResponseWrapper<BalanceDTO> checkBalance(BalanceRequest balanceRequest) {
        String validationError = balanceRequestValidation.validateBalancebalanceRequest(balanceRequest);
        if (validationError != null) {
            return new ResponseWrapper<BalanceDTO>(null, validationError);
        }
        // * Finding account based on the account number
        Optional<Account> isAccountExist = accountRepo.findByaccountNumber(balanceRequest.getAccountNumber());
        if (isAccountExist.isEmpty())
            return new ResponseWrapper<BalanceDTO>(null,
                    "Account Doesn't exist with account number: " + balanceRequest.getAccountNumber());
        Account accountExist = isAccountExist.get();

        // * Verifing pin 
        if(!accountExist.getPin().equals(balanceRequest.getPin())) return new ResponseWrapper<BalanceDTO>(null, "Wrong pin");
        
        // * Get the balance id from the account entity
        Balance balance =   accountExist.getBalance();

        BalanceDTO  balanceResponse = new BalanceDTO();
        balanceResponse.setCurrentBalance(balance.getCurrentBalance());
        balanceResponse.setLastUpdatedAt(balance.getLastUpdatedAt());

        return new ResponseWrapper<BalanceDTO>(balanceResponse, null);

    }

}
