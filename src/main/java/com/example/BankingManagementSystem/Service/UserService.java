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
import com.example.BankingManagementSystem.Encryption.EncryptionUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private EncryptionUtil encryptionUtil;
    @Autowired
    private UserAddressDetailsRepo userAddressDetailsRepo;
    @Autowired
    private UserValidations userValidations;
    @Autowired
    private UserRepo userRepo;

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    // * User registration
    public String registeringUser(UserRequest userRequest) {

        // * Validate fields
        String validationError = userValidations.validateUserFields(userRequest);
        if (validationError != null) {
            return validationError;
        }

        // Encrypt sensitive data before saving
        String encryptedAadharNumber = null;
        String encryptedEmail = null;
        String encryptedMobileNumber = null;
        try {
            // Encrypt Aadhar Number and Email
            encryptedAadharNumber = encryptionUtil.encrypt(userRequest.getAadharNumber());
            encryptedEmail = encryptionUtil.encrypt(userRequest.getEmail());
            encryptedMobileNumber = encryptionUtil.encrypt(userRequest.getMobileNumber());
        } catch (Exception e) {
            return "Error encrypting sensitive data: " + e.getMessage();
        }

        User user = new User();
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setMobileNumber(encryptedMobileNumber);
        user.setAadharNumber(encryptedAadharNumber);
        user.setEmail(encryptedEmail);

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

    // * Setting mpin for the user
    public ResponseWrapper<UserDTO> settingMpin(String mobileNumber, MpinRequest mpinRequest) {
        String encryptedMobileNumber =  null;
        try{
            encryptedMobileNumber = encryptionUtil.encrypt(mobileNumber);
        }
        catch(Exception e){
            return new ResponseWrapper<UserDTO>(null, "Error occured while encrypting");
        }
        // * Fetch user and handle case where user is not found
        Optional<User> optionalUser = userRepo.findByMobileNumber(encryptedMobileNumber);
        if (optionalUser.isEmpty()) {
            return new ResponseWrapper<UserDTO>(null, "No user found with mobile number: " + mobileNumber); // Return user not found
                                                                                           // message
        }

        // * Validate MPIN
        String mpinValidationError = userValidations.mpinValidation(mpinRequest);
        if (mpinValidationError != null) {
            return new ResponseWrapper<>(null, mpinValidationError); // Return validation error
        }

        User user = optionalUser.get();

        // * Encrypt the MPIN before saving
        try {
            String encryptedMpin = encryptionUtil.encrypt(mpinRequest.getMpin());
            user.setMpin(encryptedMpin); // Set the encrypted MPIN
        } catch (Exception e) {
            return new ResponseWrapper<>(null, "Error encrypting MPIN");
        }

        // * Set the updated timestamp and save the user
        user.setUpdatedAt(LocalDateTime.now());
        userRepo.save(user);
        
        return new ResponseWrapper<UserDTO>(null, "Mpin set successfully");
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

        // Encrypt sensitive data before saving
        String encryptedAadharNumber = null;
        String encryptedEmail = null;
        String encryptedMobileNumber = null;
        try {
            // Encrypt Aadhar Number and Email
            encryptedAadharNumber = encryptionUtil.encrypt(updateUserDetails.getAadharNumber());
            encryptedEmail = encryptionUtil.encrypt(updateUserDetails.getEmail());
            encryptedMobileNumber = encryptionUtil.encrypt(updateUserDetails.getMobileNumber());
        } catch (Exception e) {
            return new ResponseWrapper<>(null, "Error encrypting sensitive data: " + e.getMessage());
        }
        user.setMobileNumber(encryptedMobileNumber);
        user.setAadharNumber(encryptedAadharNumber);
        user.setEmail(encryptedEmail);

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

    // * Retrieving user by unique column ( we are considering mobile number as
    // unique )
    public ResponseWrapper<UserDTO> getUserByMobileNumber(String mobileNumber) {

        String encryptedMobileNumber = null;
        try {
            // Encrypt the provided mobile number before querying the database
            encryptedMobileNumber = encryptionUtil.encrypt(mobileNumber);
        } catch (Exception e) {
            return new ResponseWrapper<>(null, "Error encrypting mobile number for query: " + e.getMessage());
        }

        Optional<User> fetchingUser = userRepo.findByMobileNumber(encryptedMobileNumber);

        if (fetchingUser.isEmpty())
            return new ResponseWrapper<>(null, "user does not exist with mobile number: " + mobileNumber);

        User user = fetchingUser.get();

        // Decrypt sensitive fields (AadharNumber and Email) before returning to the
        // client
        String decryptedAadhar = null;
        String decryptedEmail = null;
        String decryptedMobileNumber = null;
        try {
            decryptedAadhar = encryptionUtil.decrypt(user.getAadharNumber()); // Decrypt Aadhar number
            decryptedEmail = encryptionUtil.decrypt(user.getEmail());
            decryptedMobileNumber = encryptionUtil.decrypt(user.getMobileNumber()); // Decrypt Email
        } catch (Exception e) {
            return new ResponseWrapper<>(null, "Error decrypting sensitive data: " + e.getMessage());
        }

        UserDTO userDTO = mapToUserDTO(user);

        // Set decrypted values into the DTO
        userDTO.setAadharNumber(decryptedAadhar); // Set decrypted Aadhar number in DTO
        userDTO.setEmail(decryptedEmail); // Set decrypted email in DTO
        userDTO.setMobileNumber(decryptedMobileNumber);

        return new ResponseWrapper<UserDTO>(userDTO, "User profile retrieved successful");
    }

    // TODO: Implement reactive programming for the below method.
    // * Retrieving all users
    public List<UserDTO> getAllUsers() {
        // Fetch all users from the repository
        List<User> users = userRepo.findAll();

        // Map the users to UserDTOs and decrypt sensitive data for admin access
        return users.stream()
                .map(user -> {
                    // Map User entity to UserDTO
                    UserDTO userDTO = mapToUserDTO(user);

                    // Decrypt sensitive fields if needed (e.g., only for admins)
                    try {
                        // Decrypt the sensitive fields
                        String decryptedAadhar = encryptionUtil.decrypt(user.getAadharNumber());
                        String decryptedEmail = encryptionUtil.decrypt(user.getEmail());
                        String decryptedMobileNumber = encryptionUtil.decrypt(user.getMobileNumber());

                        // Set decrypted values into the UserDTO
                        userDTO.setAadharNumber(decryptedAadhar);
                        userDTO.setEmail(decryptedEmail);
                        userDTO.setMobileNumber(decryptedMobileNumber);
                    } catch (Exception e) {
                        // Handle decryption errors (e.g., malformed or tampered encrypted data)
                        // Optionally log the error
                        logger.error("Decryption error: ", e);

                        userDTO.setAadharNumber("Error decrypting Aadhar");
                        userDTO.setEmail("Error decrypting Email");
                        userDTO.setMobileNumber("Error unmasking Mobile Number");
                    }

                    return userDTO;
                })
                .collect(Collectors.toList()); // Collect results into a List<UserDTO>
    }

    // * Delete user based on mobile number
    public ResponseWrapper<String> userDeletion(String mobileNumber) {
        Optional<User> findingUser = userRepo.findByMobileNumber(mobileNumber);
        if (findingUser.isEmpty()) {
            return new ResponseWrapper<>(null, "No user found with mobile number: " + mobileNumber);
        }
        userRepo.deleteByMobileNumber(mobileNumber);
        return new ResponseWrapper<>("User deleted successfully", null);
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

}