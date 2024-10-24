package com.example.BankingManagementSystem.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;
import java.time.LocalDateTime;
import com.example.BankingManagementSystem.DTO.*;
import com.example.BankingManagementSystem.Model.*;
import com.example.BankingManagementSystem.Repository.*;
import com.example.BankingManagementSystem.Request.AccountRequest;
import jakarta.transaction.Transactional;
import com.example.BankingManagementSystem.CustomClasses.*;

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
    private AccountValidation accountValidation;

    public ResponseWrapper<AccountDTO> creatingAccount(Long userId, AccountRequest accountRequest) {
        Optional<User> fetchingUser = userRepo.findById(userId);
        if (fetchingUser.isEmpty())
            return new ResponseWrapper<>(null, "User not found with ID: " + userId);

        Optional<Account> fetchingExistingAccountForUser = accountRepo.findByUser_UserId(userId);
        if (fetchingExistingAccountForUser.isPresent()) {
            return new ResponseWrapper<>(null, "Account already exists for the user with ID: " + userId);
        }

        
        // * Validate fields 
        String validationError = accountValidation.validatePin(accountRequest);
        if (validationError != null) {
            return new ResponseWrapper<>(null, validationError);
        }

        // String hashedMpin = hashMpin(accountRequest.getPin());
        User user = fetchingUser.get();
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
        accountDTO.setAccountNumber(account.getAccountNumber());
        accountDTO.setCreatedAt(account.getCreatedAt());
        accountDTO.setAccountType(account.getAccountType());
        accountDTO.setAccountStatus(account.getAccountStatus());

        return accountDTO;
    }

    // Method to hash the MPIN
    // private String hashMpin(String mpin) {
    // BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    // return encoder.encode(mpin);
    // }
}
