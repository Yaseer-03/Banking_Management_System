package com.example.BankingManagementSystem.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.BankingManagementSystem.Request.*;
import com.example.BankingManagementSystem.Service.*;

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

    //* Registering user address details
    @PostMapping("/signup/addressDetails")
    public String userAddressDetails(@RequestParam Long userId, @RequestBody UserAddressDetailsRequest userAddressDetailsRequest){
        userAddressDetailsService.addingUserAddress(userId, userAddressDetailsRequest);
        return "user address registered successfully";
    }

    //* Creating mpin 
    @PostMapping("/signup/mpin")
    public String userMpinCreation(@RequestParam Long userId, @RequestBody MpinRequest mpinRequest){
        return userService.settingMpin(userId, mpinRequest);
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
    @GetMapping("/getUser/{mobileNumber}")
    public String getMethodName(@PathVariable String userId) {
        return "return user based on user id";
    }

    //* Deleting user based on user id
    @DeleteMapping("/{userId}")
    public String deletingUser(@PathVariable Long userId){
        return "user deleted successfully";
    }   
    
}
