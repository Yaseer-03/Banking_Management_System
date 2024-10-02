package com.example.BankingManagementSystem.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.BankingManagementSystem.Request.LoginRequest;
import com.example.BankingManagementSystem.Request.UserRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController("user")
public class UserController {

    //* Registering user by taking personal details ( For reference visit userRegistration)
    @PostMapping("/signup")
    public String userRegistration(@RequestBody UserRequest userRegistration){
        return "User registered successfully";
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
