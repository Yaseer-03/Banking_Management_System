package com.example.BankingManagementSystem.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.BankingManagementSystem.DTO.AccountDTO;
import com.example.BankingManagementSystem.DTO.ResponseWrapper;
import com.example.BankingManagementSystem.Request.AccountRequest;
import com.example.BankingManagementSystem.Service.AccountService;

import java.util.List;

@RestController
@RequestMapping("/api/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    // * Creating account
    @PostMapping("/createAccount")
    public ResponseEntity<ResponseWrapper<AccountDTO>> creatingAccount(@RequestParam Long userId,
            @RequestBody(required = false) AccountRequest accountRequest) {
        ResponseWrapper<AccountDTO> response = accountService.creatingAccount(userId, accountRequest);
        return ResponseEntity.status(response.getData() == null ? HttpStatus.BAD_REQUEST : HttpStatus.CREATED)
                             .body(response);
    }

    // * Get account by user ID
    @GetMapping("/getAccount")
    public ResponseEntity<ResponseWrapper<AccountDTO>> getAccount(@RequestParam String accountNumber) {
        ResponseWrapper<AccountDTO> response = accountService.getAccount(accountNumber);
        return ResponseEntity.status(response.getData() == null ? HttpStatus.NOT_FOUND : HttpStatus.OK)
                             .body(response);
    }

    // * Get all accounts
    @GetMapping("/getAccounts")
    public ResponseEntity<ResponseWrapper<List<AccountDTO>>> getAllAccounts() {
        ResponseWrapper<List<AccountDTO>> response = accountService.getAllAccounts();
        return ResponseEntity.ok(response);
    }
}
