package com.example.BankingManagementSystem.Service;

import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.BankingManagementSystem.Model.User;
import com.example.BankingManagementSystem.Model.UserAddressDetails;
import com.example.BankingManagementSystem.Repository.*;
import com.example.BankingManagementSystem.Request.UserAddressDetailsRequest;

@Service
public class UserAddressDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserAddressDetailsRepo userAddressDetailsRepo;
    
    public void addingUserAddress(Long userId, UserAddressDetailsRequest userAddressDetailsRequest){
        // * Fetch the registered user from the database
        User user = userRepo.findById(userId).orElseThrow( () -> new NoSuchElementException("No user found with userId " + userId));
   
        // * Create a new UserAddressDetails instance
        UserAddressDetails userAddressDetails = new UserAddressDetails();
        userAddressDetails.setStreet(userAddressDetailsRequest.getStreet());
        userAddressDetails.setCity(userAddressDetailsRequest.getCity());
        userAddressDetails.setState(userAddressDetailsRequest.getState());
        userAddressDetails.setZipcode(userAddressDetailsRequest.getZipcode());

        // * Setting the user to the address details
        userAddressDetails.setUser(user);

        // * Save the address details
        userAddressDetailsRepo.save(userAddressDetails);
        
    }
}
