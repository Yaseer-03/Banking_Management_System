package com.example.BankingManagementSystem.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import com.example.BankingManagementSystem.Model.*;
import com.example.BankingManagementSystem.Repository.*;
import com.example.BankingManagementSystem.Request.AccountRequest;
import jakarta.transaction.Transactional;
import com.example.BankingManagementSystem.CustomClasses.*;
import com.example.BankingManagementSystem.Dto.*;
import com.example.BankingManagementSystem.Encryption.EncryptionUtil;

@Service
@Transactional
public class AccountService {

    @Autowired
    private AccountRepo accountRepo;

    @Autowired
    private BankRepo bankRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private BankService bankService;

    @Autowired
    private EncryptionUtil encryptionUtil;

    @Autowired
    private AccountValidation accountValidation;

    public ResponseWrapper<AccountDTO> creatingAccount(String mobileNumber, AccountRequest accountRequest) {
        String encryptedMobileNumber = null;
        try{
            encryptedMobileNumber = encryptionUtil.encrypt(mobileNumber);
        }catch(Exception e){
            return new ResponseWrapper<AccountDTO>(null, "Error occured while encrypting mobile number" + e.getMessage());
        }
        Optional<User> fetchingUser = userRepo.findByMobileNumber(encryptedMobileNumber);

        if (fetchingUser.isEmpty())
            return new ResponseWrapper<>(null, "User not found with mobile number: " + encryptedMobileNumber);

        User user = fetchingUser.get();
        Long userId = user.getUserId();
        Optional<Account> fetchingExistingAccountForUser = accountRepo.findByUser_UserId(userId);
        if (fetchingExistingAccountForUser.isPresent()) {
            return new ResponseWrapper<>(null, "Account already exists with mobile number: " + mobileNumber);
        }

        // * Validate fields
        String validationError = accountValidation.validatePin(accountRequest);
        if (validationError != null) {
            return new ResponseWrapper<>(null, validationError);
        }

        Account account = new Account();
        
        Bank fetchingBank = bankRepo.findByBankName("STATE BANK OF INDIA");

        String generatedAccountNumber = AccountNumberGenerator.generateUniqueAccountNumber();

        // Create Balance instance
        Balance balance = new Balance();
        balance.setCurrentBalance(0.0); // Initial balance
        balance.setLastUpdatedAt(LocalDateTime.now());

        // Set the account details
        account.setAccountNumber(generatedAccountNumber);
        account.setCreatedAt(LocalDateTime.now());
        account.setPin(accountRequest.getPin());
        account.setAccountType("SAVINGS");
        account.setAccountStatus("ACTIVE");
        account.setUser(user);
        BankBranch randomBranch = bankService.assignRandomBankBranchAddress();
        account.setIfscCode(randomBranch);
        account.setBank(fetchingBank);
        account.setBalance(balance); // Assign the balance to the account

        accountRepo.save(account);

        AccountDTO accountDTO = convertAccountToDTO(account);

        return new ResponseWrapper<>(accountDTO, "Account created successfully.");
    }

    public ResponseWrapper<AccountDTO> getAccount(String accountNumber) {
        Optional<Account> fetchingAccount = accountRepo.findByaccountNumber(accountNumber);
        if (fetchingAccount.isEmpty())
            return new ResponseWrapper<>(null, "Account not found for account number: " + accountNumber);

        AccountDTO accountDTO = convertAccountToDTO(fetchingAccount.get());
        return new ResponseWrapper<>(accountDTO, "Account retrieved successfully.");
    }

    public ResponseWrapper<List<AccountDTO>> getAllAccounts() {
        List<Account> accounts = accountRepo.findAll();
        List<AccountDTO> accountDTOs = accounts.stream()
                .map(this::convertAccountToDTO)
                .collect(Collectors.toList());
        return new ResponseWrapper<>(accountDTOs, "All accounts retrieved successfully.");
    }

    private AccountDTO convertAccountToDTO(Account account) {
        AccountDTO accountDTO = new AccountDTO();

        DateTimeFormatter dateAndTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss - dd/MM/yyyy");

        accountDTO.setAccountNumber(account.getAccountNumber());


        accountDTO.setCreatedAt(account.getCreatedAt().format(dateAndTimeFormatter));
        accountDTO.setAccountType(account.getAccountType());
        accountDTO.setAccountStatus(account.getAccountStatus());

         // Mapping BankBranchDTO
    BankBranchDTO bankBranchDTO = null;
    if (account.getIfscCode() != null) {
        bankBranchDTO = new BankBranchDTO();
        BankBranch bankBranch = account.getIfscCode();  // BankBranch from "ifscCode"

        // Assuming BankBranch has fields for address
        if (bankBranch != null) {
            bankBranchDTO.setId(bankBranch.getId());
            bankBranchDTO.setIfscCode(bankBranch.getIfscCode());
            bankBranchDTO.setDistrict(bankBranch.getDistrict());
            bankBranchDTO.setBranchName(bankBranch.getBranchName());
            bankBranchDTO.setBranchCode(bankBranch.getBranchCode());  // Assumes BankBranch has branchName field
            bankBranchDTO.setAddress(bankBranch.getAddress());          // Assuming street is part of BankBranch
            bankBranchDTO.setCity(bankBranch.getCity());
            bankBranchDTO.setState(bankBranch.getState());
            bankBranchDTO.setZipcode(bankBranch.getZipcode());
        }
    }
        accountDTO.setBankBranchDetails(bankBranchDTO);

        return accountDTO;
    }
}
