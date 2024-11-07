# Banking Management System Documentation

## Overview
This document details the API endpoints, additional features, and development requirements for the banking management system. It outlines the user, account, and bank operations, along with future enhancements and technical requirements.



### Add-ons & Implementations
1. All exceptions added in a separate package.
2. Null checks added (created a separate class for null value checks).
3. Transaction Type: Credit (to be added in the future).
4. Develop workflow followed during development.
5. Define transaction do's and don'ts, null checks, and other implementations.
6. Aadhar card number must be unique. **Status:** Done.
7. User service completed with added validations and null checks. **Status:** Done.
8. Encrypt sensitive data. **Status:** Need to implement.
9. Login user with mPIN and mobile number.
10. Update mPIN requires mobile number verification.

### Sensitive Data Encryption Requirements
- Aadhar card (last four digits should not be encrypted).
- Mobile number (last four digits should not be encrypted).
- mPIN.

## Testing Checklist (Updated: 11-10-2024)
- User registration with null values (check individual properties). **Status:** Done.
- User registration by breaking validations (check individual properties). **Status:** Done.
- User registration with existing email and mobile number. **Status:** Done.
- Parse error handling for date of birth input (DD-MM-YYYY to YYYY-MM-DD). **Status:** Done.
- Retrieve all users and by user ID (valid and invalid). **Status:** Done.

## User Endpoints

### 1. User Login
- **Endpoint:** `/api/user/login`
- **Method:** POST
- **Request Body:**
    ```json
    {
        "mPIN": "1234"
    }
    ```
- **Response:** Authorization Key
    ```json
    {
        "Authorization": "Bearer generated_key",
        "LoginUserId": "1"
    }
    ```

### 2. User Onboarding (Sign Up)
- **Endpoint:** `/api/user/signup/personalDetails`
- **Method:** POST
- **Request Body:**
    ```json
    {
        "firstName": "John",
        "lastName": "Doe",
        "mobileNumber": "1234567890",
        "aadharNumber": "123456789012",
        "email": "john.doe@example.com",
        "dateOfBirth": "YYYY-MM-DD",
        "mPIN": "1234"
    }
    ```
- **Response:**
    ```json
    {
        "userId": "1",
        "firstName": "John",
        "lastName": "Doe",
        "mobileNumber": "1234567890",
        "aadharNumber": "123456789012",
        "email": "john.doe@example.com",
        "dateOfBirth": "YYYY-MM-DD",
        "createdAt": "date",
        "updatedAt": "date"
    }
    ```

- **Endpoint:** `/api/user/signup/addressDetails`
- **Method:** POST
- **Request Param:** Pass the user ID generated after registration.
    - `userId: 1`
- **Request Body:**
    ```json
    {
        "street": "123 Main St",
        "city": "City",
        "state": "State",
        "zipcode": "123456"
    }
    ```
- **Response:** “User registered successfully”.

### 3. Get User by ID
- **Endpoint:** `/api/user/getuser/{userId}`
- **Method:** GET
- **Response:** If found, return the user DTO object or appropriate message.
    ```json
    {
        "Id": "1",
        "firstName": "John",
        "lastName": "Doe",
        "mobileNumber": "1234567890",
        "email": "john.doe@example.com",
        "dob": "YYYY-MM-DD",
        "role": ["Agent"],
        "createdAt": "date",
        "updatedAt": "date"
    }
    ```

### 4. Get All Users
- **Endpoint:** `/api/user/getusers`
- **Method:** GET
- **Response:**
    ```json
    [
        {
            "Id": "1",
            "firstName": "John",
            "lastName": "Doe",
            "mobileNumber": "1234567890",
            "email": "john.doe@example.com",
            "dob": "YYYY-MM-DD",
            "createdAt": "date",
            "updatedAt": "date",
            "isVerified": "verified/not verified"
        },
        {
            "Id": "2",
            "firstName": "Jane",
            "lastName": "Doe",
            "mobileNumber": "0987654321",
            "email": "jane.doe@example.com",
            "dob": "YYYY-MM-DD",
            "createdAt": "date",
            "updatedAt": "date",
            "isVerified": "verified/not verified"
        }
    ]
    ```

### 5. Update User
- **Endpoint:** `/api/user/updateuser/{mpin}`
- **Method:** PUT
- **Request Body:**
    ```json
    {
        "mobileNumber": "1234567890",
        "email": "john.doe@example.com"
    }
    ```
- **Response:**
    ```json
    {
        "Id": "1",
        "firstName": "John",
        "lastName": "Doe",
        "mobileNumber": "1234567890",
        "email": "john.doe@example.com",
        "dob": "YYYY-MM-DD",
        "role": ["Agent"],
        "createdAt": "date",
        "updatedAt": "date",
        "isVerified": "verified/not verified"
    }
    ```

### 6. Delete User
- **Endpoint:** `/api/user/deleteuser`
- **Method:** DELETE
- **Request Body:**
    ```json
    {
        "userId": "1"
    }
    ```
- **Response:** "Successful" or "Failure".

### 7. Change mPIN
- **Endpoint:** `/api/user/updateMpin`
- **Method:** POST
- **Request Body:**
    ```json
    {
        "mPIN": "1234",
        "newMpin": "4321"
    }
    ```
- **Response:** "Successful" or "Failure".

## User Address Details Endpoints
- User registration and update processes are completed with necessary validations and null checks.

## Account Endpoints

### 1. Add Account
- **Endpoint:** `/api/account/createAccount`
- **Method:** POST
- **Request Body:**
    ```json
    {
        "pin": "1234"
    }
    ```
- **Response:** Success/Failure.

### 2. Get Account by ID
- **Endpoint:** `/api/account/getaccount`
- **Method:** GET
- **Request Body:**
    ```json
    {
        "ID": "1"
    }
    ```
- **Response:**
    ```json
    {
        "Id": "1",
        "accountNumber": "482928338448",
        "accountType": "Saving/Current",
        "createdAt": "date",
        "accountStatus": "Active/Not Active"
    }
    ```

### 3. Get All Accounts
- **Endpoint:** `/api/account/getaccounts`
- **Method:** GET
- **Response:**
    ```json
    [
        {
            "Id": "1",
            "accountNumber": "482928338448",
            "accountType": "Saving/Current",
            "createdAt": "date",
            "accountStatus": "Active/Not Active"
        },
        {
            "Id": "2",
            "accountNumber": "302928338448",
            "accountType": "Saving/Current",
            "createdAt": "date",
            "accountStatus": "Active/Not Active"
        }
    ]
    ```

### 4. Delete Account
- **Endpoint:** `/api/account/deactivateAccount`
- **Method:** DELETE
- **Request Body:**
    ```json
    {
        "accountNumber": "482928338448"
    }
    ```
- **Response:** "Successful" or "Failure".

## Bank Endpoints

### 1. Add Bank
- **Endpoint:** `/api/bank/addbank`
- **Method:** POST
- **Request Body:**
    ```json
    {
        "id": "1",
        "branchName": "SBI",
        "branchCode": "SB00001",
        "branchAddress": "123 Main St",
        "zipcode": "123456"
    }
    ```
- **Response:** Success/Failure.

### 2. Get All Banks
- **Endpoint:** `/api/bank/getallbanks`
- **Method:** GET
- **Response:**
    ```json
    [
        {
            "id": "1",
            "branchName": "SBI",
            "branchCode": "SB00001",
            "branchAddress": "123 Main St",
            "zipcode": "123456"
        },
        {
            "id": "2",
            "branchName": "HDFC",
            "branchCode": "HDFC0001",
            "branchAddress": "456 Main St",
            "zipcode": "654321"
        }
    ]
    ```

### 3. Get Bank by ID
- **Endpoint:** `/api/bank/getbank/{id}`
- **Method:** GET
- **Response:**
    ```json
    {
        "id": "1",
        "branchName": "SBI",
        "branchCode": "SB00001",
        "branchAddress": "123 Main St",
        "zipcode": "123456"
    }
    ```

### 4. Delete Bank
- **Endpoint:** `/api/bank/deletebank`
- **Method:** DELETE
- **Request Body:**
    ```json
    {
        "id": "1"
    }
    ```
- **Response:** "Successful" or "Failure".


___


## WORKING ON
- **Completed**: Implementation of balance (Check balance API) [04-11-2024] {Yaseer-03}

- **completed**: Use `Math.random()` to assign a bank branch address after creating multiple branches of a bank.

## TO IMPLEMENT

1. **Encrypt Sensitive Details**:
   - **User Entity**:
     - Aadhar Card: Encrypt all but the last four digits.
     - Mobile Number: Encrypt all but the last four digits.
     - MPIN: Fully encrypt.
   - **Account Entity**:
     - Account Number: Encrypt all but the last four digits.
     - PIN: Fully encrypt.

2. **Implement Transaction Code**:
   - Develop a unique transaction code for each transaction.

3. **Global Exception Handling**:
   - Create a separate package called **Exceptions** for global exception handling.

4. **Response Entity Implementation**:
   - Implement a response entity in every controller with accurate status codes.

5. **Optimize Imports**:
   - Optimize all imports in the project at the end of the implementation.

6. **Null Checks and Validations**:
   - Implement null checks and validations for the **Account** entity and cross-check other entities as necessary.

7. **User Address Handling**:
   - Modify user requests to include address details.
   - Extract and verify address details from the user personal details request and save them in the **Address** entity.
   - **Example User Request**:
     ```json
     {
         "firstName": "{{$randomFirstName}}",
         "lastName": "{{$randomLastName}}",
         "createdAt": "{{$isoTimestamp}}",
         "address": {
             "line1": "{{$randomStreetAddress}}",
             "line2": "{{$randomCity}}",
             "country": "{{$randomCountryCode}}"
         }
     }
     ```

8. **Debit Card Transaction Handling**:
   - Include transaction details for users making a transaction through a debit card.
   - **Example Transaction Payload**:
     ```json
     {
         "userId": "{{$guid}}",
         "payment": {
             "cardNumber": "{{$randomCreditCardMask}}",
             "currency": "{{$randomCurrencyCode}}",
             "amount": {{$randomInt}},
             "confirmed": "{{$randomBoolean}}",
             "confirmedAt": "{{$isoTimestamp}}",
             "MerchantCategoryCode": "{{$merchantCategoryCode}}"
         }
     }
     ```

9. **Develop  CIF (Customer Information File).**

10. **User has to login with mobile number and mpin.**

11. **Write an endpoint to get account details for the particular user.**        
    - After user logged if user wants to get account details then in response, user has to bank details including branch, account number, balance (last transaction[optional]).
    ## Request Data

    ### Headers:
    -**Authorization**: `Bearer <JWT_Token>` (if using token-based authentication)

    ### Query Parameters:
    - **accountNumber**: `<AccountNumber>`
    - **mpin**: `<MPIN>`
    - **pin**: `<PIN>` (optional, depending on the flow)

    ### Example Request:
    ```http

    GET /account/details?accountNumber=1234567890&mpin=1234&pin=5678
    
    ```

    ## Response Data

    Upon successful authentication, the response includes:

    - **Account Number**
    - **Branch Name**
    - **Balance**
    - **Last Transaction** (Optional): This can be fetched from a transactions table if needed.

    ### Sample Response:

    ```json
    {
      "accountNumber": "1234567890",
      "branch": "Downtown Branch",
      "balance": 12000.50,
      "lastTransaction": {
        "transactionId": "TXN123456",
        "date": "2024-10-05",
        "amount": -500,
        "description": "ATM Withdrawal"
        }
    }
    ```

12.**Account Limitations**
   - Each user can only have one account (Savings).


   ## Add-ons & Implementations

- **Exception Handling:** Separate package for custom exceptions.
- **Null Checks:** A dedicated class handles null value checks.
- **Transaction Types:** Currently supporting debit; credit to be added.
- **Unique Fields:** Aadhar number is unique. **Status:** Implemented.
- **Validations & Security:** User validations and null checks in place. **Status:** Implemented.
- **Data Encryption:** Sensitive data (Aadhar, mobile number, mPIN) encryption in progress.
- **Login:** Allows mPIN-based login with mobile verification.
- **mPIN Update:** Requires mobile number verification.
- **Unique Transaction Code Generation** : For each transaction.
- **Global Exception Handling**: Add to Exceptions package.
- **Response Entity with Status Codes**: Standardize responses.
- **Optimize Imports**: Clean up imports post-development.
- **Null Checks & Validations**: Verify in Account and other entities.
- **Reactive Programming**: Don't wait till everything is fetched, get the response which is fetched first.
- **User Address Handling in Registration**: Save address data separately in the -Address entity.








## Notes
- Keep track of all updates and changes.
- Ensure to run all tests before deployment.
