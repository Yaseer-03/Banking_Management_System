package com.example.BankingManagementSystem.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.BankingManagementSystem.DTO.*;
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
    public String loginUser(@RequestParam LoginRequest loginRequest) { 
        return "user logged in successfully";
    }

    //* Updating user details based on the mobile number
    @PutMapping("/updateUser/{mobileNumber}")
    public ResponseWrapper<UserDTO> updatingUser(@PathVariable String mobileNumber, @RequestBody(required = false) UserRequest updateUserDetails){
        return userService.updatingUserDetails(mobileNumber, updateUserDetails);
        
    }

    // @PutMapping("/updateMpin")
    // public String updatingMpin(@)

    //* Retrieving all user's
    @GetMapping("/getUsers")
    public List<UserDTO> getMethodName() {
        return userService.getAllUsers();
    }

    //* Retrieving single user based on user id 
    @GetMapping("/getUser/{mobileNumber}")
    public ResponseWrapper<UserDTO> getMethodName(@PathVariable String mobileNumber) {
        return userService.getUserByMobileNumber(mobileNumber);
    }

    //* Deleting user based on user id
    @DeleteMapping("/{mobileNumber}")
    public String deletingUser(@PathVariable String mobileNumber){
        return userService.userDeletion(mobileNumber);
    }   
    
}
