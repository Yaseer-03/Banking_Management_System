package com.example.BankingManagementSystem.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.BankingManagementSystem.DTO.BankBranchAddress;
import com.example.BankingManagementSystem.DTO.BankDTO;
import com.example.BankingManagementSystem.DTO.ResponseWrapper;
import com.example.BankingManagementSystem.Request.BankRequest;
import com.example.BankingManagementSystem.Service.BankService;

@RestController
@RequestMapping("/api/bank")
public class BankController {

    @Autowired
    private BankService bankService;

    @PostMapping("/addBank")
    public ResponseWrapper<BankDTO> addBank(@RequestBody BankRequest bankRequest){
        return bankService.addBank(bankRequest);
    }

    // @PostMapping("/addBankAddress")
    // public ResponseWrapper<BankBranchAddressDTO>
}
