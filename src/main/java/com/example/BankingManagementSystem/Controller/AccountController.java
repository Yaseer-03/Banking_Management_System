package com.example.BankingManagementSystem.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.BankingManagementSystem.Dto.AccountDTO;
import com.example.BankingManagementSystem.Dto.ResponseWrapper;
import com.example.BankingManagementSystem.Request.AccountRequest;
import com.example.BankingManagementSystem.Service.AccountService;
import com.example.BankingManagementSystem.ResponseStatusCode.ResponseService;

import java.util.List;

@RestController
@RequestMapping("/api/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private ResponseService responseService;
    
    // * Creating account
    @PostMapping("/create-account")
    public ResponseEntity<ResponseWrapper<AccountDTO>> creatingAccount(@RequestParam String mobileNumber,
            @RequestBody(required = false) AccountRequest accountRequest) {

        ResponseWrapper<AccountDTO> response = accountService.creatingAccount(mobileNumber, accountRequest);

        // Using ResponseService to return a response with the appropriate status
        if (response.getData() == null) {
            return responseService.createErrorResponse(response.getMessage());  // 400 Bad Request
        } else {
            return responseService.createCreatedResponse(response.getData());  // 201 Created
        }
    }

    // * Get account by account number
    @GetMapping("/get-account")
    public ResponseEntity<ResponseWrapper<AccountDTO>> getAccount(@RequestParam String accountNumber) {
        ResponseWrapper<AccountDTO> response = accountService.getAccount(accountNumber);
        
        // Using ResponseService to return the correct response
        if (response.getData() == null) {
            return responseService.createNotFoundResponse(response.getMessage());  // 404 Not Found
        } else {
            return responseService.createSuccessResponse(response.getData());  // 200 OK
        }
    }

    // * Get all accounts
    @GetMapping("/get-accounts")
    public ResponseEntity<ResponseWrapper<List<AccountDTO>>> getAllAccounts() {
        ResponseWrapper<List<AccountDTO>> response = accountService.getAllAccounts();
        
        // Success response for retrieving all accounts
        return responseService.createSuccessResponse(response.getData());  // 200 OK
    }
}
