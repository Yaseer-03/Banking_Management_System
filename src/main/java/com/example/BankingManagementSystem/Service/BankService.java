package com.example.BankingManagementSystem.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.BankingManagementSystem.Dto.BankDTO;
import com.example.BankingManagementSystem.Dto.ResponseWrapper;
import com.example.BankingManagementSystem.Model.Bank;
import com.example.BankingManagementSystem.Model.BankBranch;
import com.example.BankingManagementSystem.Repository.BankBranchRepo;
import com.example.BankingManagementSystem.Repository.BankRepo;
import com.example.BankingManagementSystem.Request.BankRequest;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class BankService {

    @Autowired
    private BankBranchRepo bankBranchRepo;
    @Autowired
    private BankRepo bankRepo;

    public BankDTO mapToDto(Bank savedBank) {
        BankDTO convertingBankDTO = new BankDTO();
        convertingBankDTO.setId(savedBank.getId());
        convertingBankDTO.setBankName(savedBank.getBankName());
        return convertingBankDTO;
    }

    public BankBranch assignRandomBankBranchAddress() {
        // Fetch all bank branches from the repository
        List<BankBranch> branches = bankBranchRepo.findAll();

        if (branches.isEmpty()) {
            throw new IllegalStateException("No branches found.");
        }
        // Generate a random index within the range of branch list size
        int randomIndex = (int) (Math.random() * branches.size());

        BankBranch randomBranch = branches.get(randomIndex);
        return randomBranch;
    }

    public ResponseWrapper<BankDTO> addBank(BankRequest bankRequest) {
        Bank bank = new Bank();
        bank.setBankName(bankRequest.getBankName());
        Bank savedBank = bankRepo.save(bank);
        BankDTO response = mapToDto(savedBank);
        return new ResponseWrapper<>(response, null);
    }
}
