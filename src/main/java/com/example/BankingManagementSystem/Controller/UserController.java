package com.example.BankingManagementSystem.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.BankingManagementSystem.Request.UserRegistration;

@RestController
public class UserController {

    @PostMapping
    public String userRegistration(@RequestBody UserRegistration userRegistration){
        return "User registered successfully";
        
    }

}
