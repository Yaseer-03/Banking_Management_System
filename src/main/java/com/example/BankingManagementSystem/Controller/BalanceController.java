package com.example.BankingManagementSystem.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.BankingManagementSystem.DTO.BalanceDTO;
import com.example.BankingManagementSystem.DTO.ResponseWrapper;
import com.example.BankingManagementSystem.Request.BalanceRequest;
import com.example.BankingManagementSystem.Service.AccountService;
import com.example.BankingManagementSystem.Service.BalanceService;

@RestController
@RequestMapping("/api/balance")
public class BalanceController {

    @Autowired
    BalanceService balanceService;
    
    // @PostMapping("/checkBalance") // Take account number and pin to check balance
    // public ResponseEntity<ResponseWrapper<BalanceDTO>> getBalance(@RequestBody BalanceRequest balanceRequest){
    //         ResponseWrapper<BalanceDTO> balanceDetails = balanceService.checkBalance(balanceRequest);
    //         return ResponseEntity.ok(balanceDetails);
    // }
}