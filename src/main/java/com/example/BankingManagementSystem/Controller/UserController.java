package com.example.BankingManagementSystem.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.BankingManagementSystem.Dto.*;
import com.example.BankingManagementSystem.Request.*;
import com.example.BankingManagementSystem.Service.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserAddressDetailsService userAddressDetailsService;

    // * Registering user by taking personal details
    @PostMapping("/signup/personalDetails")
    public String userRegistration(@RequestBody UserRequest userRegistration) {
        return userService.registeringUser(userRegistration);
    }

    // * Creating mpin
    @PostMapping("/signup/mpin")
    public ResponseWrapper<UserDTO> userMpinCreation(@RequestParam String mobileNumber,@RequestBody(required = false) MpinRequest mpinRequest) {
        return userService.settingMpin(mobileNumber, mpinRequest);
    }

    // * Updating user details based on the mobile number
    @PutMapping("/updateUser/{mobileNumber}")
    public ResponseWrapper<UserDTO> updatingUser(@PathVariable String mobileNumber,
            @RequestBody(required = false) UserRequest updateUserDetails) {
        return userService.updatingUserDetails(mobileNumber, updateUserDetails);
    }

    // * Registering user address details
    @PostMapping("/signup/addressDetails")
    public ResponseWrapper<UserAddressDetailsDTO> userAddressDetails(@RequestParam Long userId,
            @RequestBody UserAddressDetailsRequest userAddressDetailsRequest) {
        return userAddressDetailsService.addingUserAddress(userId, userAddressDetailsRequest);
    }

    // * Updating user address details
    @PutMapping("/update/addressDetails")
    public ResponseWrapper<UserAddressDetailsDTO> updateUserAddressDetails(@RequestParam String mobileNumber,
            @RequestBody(required = false) UserAddressDetailsRequest userAddressDetailsRequest) {
        return userAddressDetailsService.updateAddressDetails(mobileNumber, userAddressDetailsRequest);
    }

    // * Retrieving all user's
    @GetMapping("/getUsers")
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    // * Retrieving single user based on user id
    @GetMapping("/getUser/{mobileNumber}")
    public ResponseWrapper<UserDTO> getUser(@PathVariable String mobileNumber) {
        return userService.getUserByMobileNumber(mobileNumber);
    }

    // * Deleting user based on user id
    @DeleteMapping("/{mobileNumber}")
    public ResponseWrapper<String> deletingUser(@PathVariable String mobileNumber) {
        return userService.userDeletion(mobileNumber);
    }

    // * Login in user based on the Mpin ( take the mpin from the user )
    @PostMapping("/login")
    public String loginUser(@RequestParam LoginRequest loginRequest) {
        return "user logged in successfully";
    }

    // @PutMapping("/updateMpin")
    // public String updatingMpin(@)

}
