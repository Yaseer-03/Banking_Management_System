package com.example.BankingManagementSystem.Controller;

import org.springframework.web.bind.annotation.*;
import com.example.BankingManagementSystem.Request.TransactionRequest;


@RestController
@RequestMapping("/api/transaction")
public class TransactionController {
    
    @PostMapping("/makeTransaction")
    public String makeTransaction(@RequestBody TransactionRequest transactionRequest){
        return "Transaction initiated successfully";
    }

    @GetMapping("/{userId}")
    public String getMethodName(@PathVariable String userId) {
        return "return transactions of respective user";
    }
    
}
