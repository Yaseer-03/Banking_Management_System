package com.example.BankingManagementSystem.Service;

import java.time.ZoneId;
import com.example.BankingManagementSystem.Validations.UserValidations;
import java.time.ZonedDateTime;
import java.util.stream.Collectors;
import java.util.List;
import com.example.BankingManagementSystem.DTO.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.BankingManagementSystem.Model.*;
import com.example.BankingManagementSystem.Repository.UserRepo;
import com.example.BankingManagementSystem.Request.*;

@Service
public class UserService {

    @Autowired
    private UserValidations userValidations;
    @Autowired
    private UserRepo userRepo;

    // * User registration
    public String registeringUser(UserRequest userRequest) {

        // Validate fields
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
        user.setDateOfBirth(userRequest.getDateOfBirth());
        user.setMpin(userRequest.getMpin());
        user.setRole("Consumer");

        ZonedDateTime timeAndDateInIST = ZonedDateTime.now(ZoneId.of("Asia/Kolkata"));
        user.setCreatedAt(timeAndDateInIST.toLocalDateTime());
        user.setUpdatedAt(timeAndDateInIST.toLocalDateTime());

        userRepo.save(user);
        return "User registered successfully" + "User id is: " + user.getUserId();
    }

    // * Retrieving all users 
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepo.findAll();
        List<UserDTO> userDTOs = users.stream()
                .map(this::mapToUserDTO)
                .collect(Collectors.toList());
        return userDTOs;
    }

    // * Retrieving user by unique column ( we are considering mobile number )
    public UserDTO getUserByMobileNumber(String mobileNumber) {
        User user = userRepo.findByMobileNumber();
        return mapToUserDTO(user);
    }

    // * Method to map User entity to UserDTO
    private UserDTO mapToUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(user.getUserId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setMobileNumber(user.getMobileNumber());
        userDTO.setEmail(user.getEmail());
        userDTO.setDateOfBirth(user.getDateOfBirth());
        userDTO.setCreatedAt(user.getCreatedAt());
        userDTO.setUpdatedAt(user.getUpdatedAt());
        return userDTO;
    }

}