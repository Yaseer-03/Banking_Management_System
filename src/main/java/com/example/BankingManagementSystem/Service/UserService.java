package com.example.BankingManagementSystem.Service;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.BankingManagementSystem.DTO.UserDTO;
import com.example.BankingManagementSystem.Model.*;
import com.example.BankingManagementSystem.Repository.UserRepo;
import com.example.BankingManagementSystem.Request.*;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    // * User registration
    public UserDTO registeringUser(UserRequest userRequest) {

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

        User savedUser = userRepo.save(user);
        return mapToUserDTO(savedUser);
    }

    public UserDTO mapToUserDTO(User user){
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