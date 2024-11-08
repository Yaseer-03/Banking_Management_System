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
import com.example.BankingManagementSystem.Request.BalanceRequest;
import jakarta.transaction.Transactional;
import com.example.BankingManagementSystem.CustomClasses.*;
import com.example.BankingManagementSystem.Dto.*;

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
        BankBranchAddress branchAddressId = bankService.assignRandomBankBranchAddress();
        account.setBankBranchAddress(branchAddressId);
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

        BankBranchAddressDTO branchAddressDTO = null;
        // Set BankBranchAddressDTO
        if (account.getBankBranchAddress() != null) {
            branchAddressDTO = new BankBranchAddressDTO();
            branchAddressDTO.setId(account.getBankBranchAddress().getId());
            branchAddressDTO.setBranchName(account.getBankBranchAddress().getBranchName());
            branchAddressDTO.setStreet(account.getBankBranchAddress().getState());
            branchAddressDTO.setCity(account.getBankBranchAddress().getCity());
            branchAddressDTO.setState(account.getBankBranchAddress().getState());
            branchAddressDTO.setZipcode(account.getBankBranchAddress().getZipcode());

        }
        accountDTO.setBankBranchAddressDTO(branchAddressDTO);

        return accountDTO;
    }

    // Method to hash the MPIN
    // private String hashMpin(String mpin) {
    // BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    // return encoder.encode(mpin);
    // }
}
