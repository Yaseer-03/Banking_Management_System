package com.example.BankingManagementSystem.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import jakarta.transaction.Transactional;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;
import java.util.List;
import java.util.Optional;
import com.example.BankingManagementSystem.CustomClasses.UserValidations;
import com.example.BankingManagementSystem.Dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.BankingManagementSystem.Model.*;
import com.example.BankingManagementSystem.Repository.UserAddressDetailsRepo;
import com.example.BankingManagementSystem.Repository.UserRepo;
import com.example.BankingManagementSystem.Request.*;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserAddressDetailsRepo userAddressDetailsRepo;
    @Autowired
    private UserValidations userValidations;
    @Autowired
    private UserRepo userRepo;

    // * User registration
    public String registeringUser(UserRequest userRequest) {

        // * Validate fields
        String validationError = userValidations.validateUserFields(userRequest);
        if (validationError != null) {
            return validationError;
        }

        User user = new User();
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setMobileNumber(userRequest.getMobileNumber());
        user.setAadharNumber(userRequest.getAadharNumber());
        user.setEmail(userRequest.getEmail());

        // Validate and parse the date of birth using UserValidations
        try {
            // LocalDate dateOfBirth =
            // userValidations.isDateOfBirthValid(userRequest.getDateOfBirth());
            LocalDate dateOfBirth = userValidations.isDateOfBirthValid(userRequest.getDateOfBirth());
            userRequest.setDateOfBirth(dateOfBirth.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))); // Format it back
                                                                                                       // if necessary
            user.setDateOfBirth(dateOfBirth); // Store as LocalDate
        } catch (IllegalArgumentException e) {
            return e.getMessage(); // Return the error message from validation
        }

        user.setRole("Consumer");

        ZonedDateTime timeAndDateInIST = ZonedDateTime.now(ZoneId.of("Asia/Kolkata"));
        user.setCreatedAt(timeAndDateInIST.toLocalDateTime());
        user.setUpdatedAt(timeAndDateInIST.toLocalDateTime());

        userRepo.save(user);
        return "User registered successfully" + "\n User id is: " + user.getUserId()
                + "\n Please use the above user id to register you address details";
    }

    // TODO: Implement reactive programming for the below method.
    // * Retrieving all users
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepo.findAll();
        List<UserDTO> userDTOs = users.stream()
                .map(this::mapToUserDTO)
                .collect(Collectors.toList());
        return userDTOs;
    }

    // * Retrieving user by unique column ( we are considering mobile number as
    // unique )
    public ResponseWrapper<UserDTO> getUserByMobileNumber(String mobileNumber) {
        Optional<User> fetchingUser = userRepo.findByMobileNumber(mobileNumber);

        if (fetchingUser.isEmpty())
            return new ResponseWrapper<>(null, "user does not exist with mobile number: " + mobileNumber);

        User user = fetchingUser.get();

        UserDTO userDTO = mapToUserDTO(user);

        return new ResponseWrapper<UserDTO>(userDTO, "User profile retrieved successful");
    }

    // * Method to map User entity to UserDTO
    private UserDTO mapToUserDTO(User user) {
        UserDTO userDTO = new UserDTO();

        // Define the date formatter
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter dateAndTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss - dd/MM/yyyy");

        userDTO.setUserId(user.getUserId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setMobileNumber(user.getMobileNumber());
        userDTO.setEmail(user.getEmail());

        // Format dates and set them
        userDTO.setDateOfBirth(user.getDateOfBirth().format(dateFormatter));
        userDTO.setCreatedAt(user.getCreatedAt().format(dateAndTimeFormatter));
        userDTO.setUpdatedAt(user.getUpdatedAt().format(dateAndTimeFormatter));
        // userDTO.setDateOfBirth(user.getDateOfBirth());
        // userDTO.setCreatedAt(user.getCreatedAt());
        // userDTO.setUpdatedAt(user.getUpdatedAt());
        Optional<UserAddressDetails> fetchingUserAddressDetails = userAddressDetailsRepo
                .findByUser_UserId(user.getUserId());
        fetchingUserAddressDetails.ifPresent(addressDetails -> {
            UserAddressDetailsDTO addressDTO = new UserAddressDetailsDTO();
            addressDTO.setId(addressDetails.getId());
            addressDTO.setStreet(addressDetails.getStreet());
            addressDTO.setCity(addressDetails.getCity());
            addressDTO.setDistrict(addressDetails.getDistrict());
            addressDTO.setState(addressDetails.getState());
            addressDTO.setZipcode(addressDetails.getZipcode());
            userDTO.setUserAddressDetails(addressDTO);
        });
        return userDTO;
    }

    // * Setting mpin for the user
    public ResponseWrapper<UserDTO> settingMpin(Long userId, MpinRequest mpinRequest) {

        // * Fetch user and handle case where user is not found
        Optional<User> optionalUser = userRepo.findById(userId);
        if (optionalUser.isEmpty()) {
            return new ResponseWrapper<UserDTO>(null, "No user found with id: " + userId); // Return user not found
                                                                                           // message
        }

        // * Validate MPIN
        String mpinValidationError = userValidations.mpinValidation(mpinRequest);
        if (mpinValidationError != null) {
            return new ResponseWrapper<>(null, mpinValidationError); // Return validation error
        }

        User user = optionalUser.get();
        user.setMpin(mpinRequest.getMpin());
        user.setUpdatedAt(LocalDateTime.now());
        userRepo.save(user);

        UserDTO userDTO = mapToUserDTO(user);
        return new ResponseWrapper<UserDTO>(userDTO, "User profile created successfully");
    }

    // * updating user details based on mobile number
    public ResponseWrapper<UserDTO> updatingUserDetails(String mobileNumber, UserRequest updateUserDetails) {
        Optional<User> fetchingUser = userRepo.findByMobileNumber(mobileNumber);
        if (fetchingUser.isEmpty())
            return new ResponseWrapper<>(null, "No user found with mobile number: " + mobileNumber);
        User user = fetchingUser.get();

        // If user exists, check if the request body is provided
        if (updateUserDetails == null) {
            return new ResponseWrapper<>(null, "Request body is required to update user details.");
        }

        // * Validate fields
        String validationError = userValidations.validatingUserFields(updateUserDetails, user.getMobileNumber(),
                user.getAadharNumber(), user.getEmail());
        if (validationError != null) {
            return new ResponseWrapper<>(null, validationError);
        }

        user.setFirstName(updateUserDetails.getFirstName());
        user.setLastName(updateUserDetails.getLastName());
        user.setMobileNumber(updateUserDetails.getMobileNumber());
        user.setAadharNumber(updateUserDetails.getAadharNumber());
        user.setEmail(updateUserDetails.getEmail());
        // user.setDateOfBirth(updateUserDetails.getDateOfBirth());
        // Validate and parse the date of birth using UserValidations
        try {
            LocalDate dateOfBirth = userValidations.isDateOfBirthValid(updateUserDetails.getDateOfBirth());
            user.setDateOfBirth(dateOfBirth); // Store as LocalDate
        } catch (IllegalArgumentException e) {
            return new ResponseWrapper<>(null, e.getMessage()); // Return the error message from validation
        }

        user.setUpdatedAt(LocalDateTime.now());
        userRepo.save(user);
        UserDTO convertingToUserDTO = mapToUserDTO(user);
        return new ResponseWrapper<UserDTO>(convertingToUserDTO, "User Details updated successfully");

    }

    // * Delete user based on mobile number
    public String userDeletion(String mobileNumber) {
        Optional<User> findingUser = userRepo.findByMobileNumber(mobileNumber);
        if (findingUser.isEmpty()) {
            return "No user found with mobile number: " + mobileNumber;
        }
        userRepo.deleteByMobileNumber(mobileNumber);
        return "user deleted successfully";
    }

}