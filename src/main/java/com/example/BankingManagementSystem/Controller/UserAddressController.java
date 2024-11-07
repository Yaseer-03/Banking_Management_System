package com.example.BankingManagementSystem.Controller;

import org.springframework.web.bind.annotation.*;

import com.example.BankingManagementSystem.Dto.UserAddressDetailsDTO;
import com.example.BankingManagementSystem.Request.UserAddressDetailsRequest;

@RestController
@RequestMapping("/api/Address")
public class UserAddressController{
    
    //* Retrieving address based on user id, add respones also
    @GetMapping("/getAddress/{userId}")
    public String getUserAddress(@PathVariable UserAddressDetailsDTO userAddressDetails){
        return "get user details";
    }

    //* Adding address for the user after completing user registration with personal details
    @PostMapping("/addAddress")
    public String addAddress(@RequestBody UserAddressDetailsRequest userAddressDetailsRequest){
        return "add address";
    }

    @PutMapping("/updateAddress/{id}")
    public String updateAddress(@PathVariable String id, @RequestBody UserAddressDetailsRequest userAddressDetailsRequest) {
        return "User address updated successfully";
    }

}
