package com.example.BankingManagementSystem.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BankingManagementSystem.Dto.BankBranchAddressDTO;
import com.example.BankingManagementSystem.Dto.BankDTO;
import com.example.BankingManagementSystem.Dto.ResponseWrapper;
import com.example.BankingManagementSystem.Model.Bank;
import com.example.BankingManagementSystem.Model.BankBranchAddress;
import com.example.BankingManagementSystem.Repository.BankBranchAddressRepo;
import com.example.BankingManagementSystem.Repository.BankRepo;
import com.example.BankingManagementSystem.Request.BankRequest;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class BankService {

    @Autowired
    private BankBranchAddressRepo bankBranchAddressRepo;
    @Autowired
    private BankRepo bankRepo;

    public BankDTO mapToDto(Bank savedBank) {
        BankDTO convertingBankDTO = new BankDTO();
        convertingBankDTO.setId(savedBank.getId());
        convertingBankDTO.setBankName(savedBank.getBankName());
        return convertingBankDTO;
    }

    public BankBranchAddress assignRandomBankBranchAddress() {
        // Fetch all bank branches from the repository
        List<BankBranchAddress> branches = bankBranchAddressRepo.findAll();

        if (branches.isEmpty()) {
            throw new IllegalStateException("No branches found.");
        }
        // Generate a random index within the range of branch list size
        int randomIndex = (int) (Math.random() * branches.size());
        return branches.get(randomIndex);
    }

    public ResponseWrapper<BankDTO> addBank(BankRequest bankRequest) {
        Bank bank = new Bank();
        bank.setBankName(bankRequest.getBankName());
        Bank savedBank = bankRepo.save(bank);
        BankDTO response = mapToDto(savedBank);
        return new ResponseWrapper<>(response, null);
    }
}
