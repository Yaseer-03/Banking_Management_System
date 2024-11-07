package com.example.BankingManagementSystem.Service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import java.time.LocalDateTime;
import com.example.BankingManagementSystem.CustomClasses.UserValidations;
import com.example.BankingManagementSystem.Dto.ResponseWrapper;
import com.example.BankingManagementSystem.Dto.UserAddressDetailsDTO;
import com.example.BankingManagementSystem.Model.User;
import com.example.BankingManagementSystem.Model.UserAddressDetails;
import com.example.BankingManagementSystem.Repository.*;
import com.example.BankingManagementSystem.Request.UserAddressDetailsRequest;

@Service
public class UserAddressDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserValidations userValidations;

    @Autowired
    private UserAddressDetailsRepo userAddressDetailsRepo;

    // * Convert UserAddressDetails entity to DTO
    public UserAddressDetailsDTO mapToUserAddressDetailsDTO(UserAddressDetails userAddressDetails) {
        UserAddressDetailsDTO userAddressDetailsDTO = new UserAddressDetailsDTO();
        userAddressDetailsDTO.setStreet(userAddressDetails.getStreet());
        userAddressDetailsDTO.setCity(userAddressDetails.getCity());
        userAddressDetailsDTO.setState(userAddressDetails.getState());
        userAddressDetailsDTO.setZipcode(userAddressDetails.getZipcode());
        userAddressDetailsDTO.setId(userAddressDetails.getUser().getUserId());
        return userAddressDetailsDTO;
    }

    // * Adding address detailss
    public ResponseWrapper<UserAddressDetailsDTO> addingUserAddress(Long userId,
            UserAddressDetailsRequest userAddressDetailsRequest) {
        // * Fetch the registered user from the database
        Optional<User> fetchingUser = userRepo.findById(userId);
        if (fetchingUser.isEmpty())
            return new ResponseWrapper<UserAddressDetailsDTO>(null, "user not found with id: " + userId);

        String addressValidation = userValidations.nullCheckForUserAddress(userAddressDetailsRequest);
        if (addressValidation != null) {
            return new ResponseWrapper<>(null, addressValidation); // * Returning the error if the validation failes
        }

        // * Validate the user address request before updating
        String validatingFields = userValidations.nullCheckForUserAddress(userAddressDetailsRequest);
        if (validatingFields != null) {
            return new ResponseWrapper<>(null, validatingFields);
        }
        // * Create a new UserAddressDetails instance
        UserAddressDetails userAddressDetails = new UserAddressDetails();
        userAddressDetails.setStreet(userAddressDetailsRequest.getStreet());
        userAddressDetails.setCity(userAddressDetailsRequest.getCity());
        userAddressDetails.setState(userAddressDetailsRequest.getState());
        userAddressDetails.setZipcode(userAddressDetailsRequest.getZipcode());

        User user = fetchingUser.get();

        user.setUpdatedAt(LocalDateTime.now());
        userRepo.save(user);

        // * Setting the user to the address details
        userAddressDetails.setUser(user);

        // * Save the address details
        userAddressDetailsRepo.save(userAddressDetails);

        UserAddressDetailsDTO convertingToDto = mapToUserAddressDetailsDTO(userAddressDetails);
        return new ResponseWrapper<UserAddressDetailsDTO>(convertingToDto, null);

    }

    // * Update existing address details for a user
    public ResponseWrapper<UserAddressDetailsDTO> updateAddressDetails(String mobileNumber,
            @RequestBody(required = false) UserAddressDetailsRequest userAddressDetailsRequest) {

        Optional<User> fetchingUser = userRepo.findByMobileNumber(mobileNumber);
        if (fetchingUser.isEmpty()) {
            return new ResponseWrapper<>(null, "User does not exist with mobile number: " + mobileNumber);
        }

        User user = fetchingUser.get();

        // * Fetch existing UserAddressDetails
        Optional<UserAddressDetails> existingAddressOpt = userAddressDetailsRepo.findByUser_UserId(user.getUserId());
        if (existingAddressOpt.isEmpty()) {
            return new ResponseWrapper<>(null,
                    "Address details not found for user with mobile number: " + mobileNumber);
        }

        // * Validate the user address request before updating
        String addressValidation = userValidations.nullCheckForUserAddress(userAddressDetailsRequest);
        if (addressValidation != null) {
            return new ResponseWrapper<>(null, addressValidation);
        }

        UserAddressDetails existingAddress = existingAddressOpt.get();

        // * Update the address details with the new information
        existingAddress.setStreet(userAddressDetailsRequest.getStreet());
        existingAddress.setCity(userAddressDetailsRequest.getCity());
        existingAddress.setState(userAddressDetailsRequest.getState());
        existingAddress.setZipcode(userAddressDetailsRequest.getZipcode());

        // * Update the timestamp for the user
        user.setUpdatedAt(LocalDateTime.now());
        userRepo.save(user);

        // * Save the updated address details
        UserAddressDetails updatedAddress = userAddressDetailsRepo.save(existingAddress);

        // * Convert to DTO and return as response
        UserAddressDetailsDTO userAddressDetailsDTO = mapToUserAddressDetailsDTO(updatedAddress);
        return new ResponseWrapper<>(userAddressDetailsDTO, null);
    }
}
