package com.example.BankingManagementSystem.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.example.BankingManagementSystem.CustomClasses.BalancebalanceRequestValidation;
import com.example.BankingManagementSystem.DTO.BalanceDTO;
import com.example.BankingManagementSystem.DTO.ResponseWrapper;
import com.example.BankingManagementSystem.Model.Account;
import com.example.BankingManagementSystem.Repository.AccountRepo;
import com.example.BankingManagementSystem.Request.BalanceRequest;

@Service
public class BalanceService {

    @Autowired
    BalancebalanceRequestValidation balancebalanceRequestValidation;
    @Autowired
    AccountRepo accountRepo;

    //TODO: Validate the incoming request based on the validations and validate pin correct and show the balance the particular user;
    // public ResponseWrapper<BalanceDTO> checkBalance(BalanceRequest balanceRequest){
    //     String validationError = balancebalanceRequestValidation.validateBalancebalanceRequest(balanceRequest);
    //     if(validationError != null){
    //      return new ResponseWrapper<BalanceDTO>(null, validationError);
    //     }
    // }

}
