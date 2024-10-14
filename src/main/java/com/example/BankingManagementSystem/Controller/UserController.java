package com.example.BankingManagementSystem.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.BankingManagementSystem.Request.LoginRequest;
import com.example.BankingManagementSystem.Request.UserAddressDetailsRequest;
import com.example.BankingManagementSystem.Request.UserRequest;
import com.example.BankingManagementSystem.Service.UserAddressDetailsService;
import com.example.BankingManagementSystem.Service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserAddressDetailsService userAddressDetailsService;

    //* Registering user by taking personal details
    @PostMapping("/signup/personalDetails")
    public String userRegistration(@RequestBody UserRequest userRegistration){
        return userService.registeringUser(userRegistration);
    }

    @PostMapping("/signup/addressDetails")
    public String userAddressDetails(@RequestParam Long userId, @RequestBody UserAddressDetailsRequest userAddressDetailsRequest){
        userAddressDetailsService.addingUserAddress(userId, userAddressDetailsRequest);
        return "user address registered successfully";
    }

    //* Login in user based on the Mpin ( take the mpin from the user )
    @PostMapping("/login")
    public String loginUser(@RequestParam LoginRequest mPin) { 
        return "user logged in successfully";
    }

    //* Updating user details based on the user id
    @PutMapping("/updateuser/{userid}")
    public String updatingUser(@PathVariable Long userId, @RequestBody UserRequest userRegistration){
        return "User details updated successfully";
    }

    //* Retrieving all user's
    @GetMapping("/getUsers")
    public String getMethodName() {
        return "All users";
    }

    //* Retrieving single user based on user id 
    @GetMapping("/getUser")
    public String getMethodName(@PathVariable String userId) {
        return "return user based on user id";
    }

    //* Deleting user based on user id
    @DeleteMapping("/user/{userId}")
    public String deletingUser(@PathVariable Long userId){
        return "user deleted successfully";
    }   
    
}
