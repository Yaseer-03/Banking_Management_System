package com.example.BankingManagementSystem.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BankingManagementSystem.DTO.BankDTO;
import com.example.BankingManagementSystem.DTO.ResponseWrapper;
import com.example.BankingManagementSystem.Model.Bank;
import com.example.BankingManagementSystem.Repository.BankRepo;
import com.example.BankingManagementSystem.Request.BankRequest;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class BankService {

    @Autowired
    private BankRepo bankRepo;

    //* Adding bank 
    public ResponseWrapper<BankDTO> addBank(BankRequest bankRequest) {
        Bank bank = new Bank();
        bank.setBankName(bankRequest.getBankName());
        bank.setIfscCode(bankRequest.getIfscCode());
        Bank savedBank = bankRepo.save(bank);
        BankDTO convertingBankDTO = new BankDTO();
        convertingBankDTO.setId(savedBank.getId());
        convertingBankDTO.setBankName(savedBank.getBankName());
        convertingBankDTO.setIfscCode(savedBank.getIfscCode());
        return new ResponseWrapper<BankDTO>(convertingBankDTO, null);
    }


    
}
