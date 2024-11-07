# Account API Documentation

This document outlines the endpoints for managing account-related operations such as adding, retrieving, and deactivating accounts.

## Account Endpoints

### 1. Add Account
- **Endpoint:** `/api/account/createAccount`
- **Method:** `POST`
- **Request Body:**
    ```json
    {
        "pin": "1234"
    }
    ```
- **Response:** Success/Failure.

### 2. Get Account by ID
- **Endpoint:** `/api/account/getaccount`
- **Method:** `GET`
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
- **Method:** `GET`
- **Response:**
    ```json
    [
      {
            "accountNumber": "56082171586",
            "accountType": "SAVINGS",
            "createdAt": "2024-10-17T17:08:50.823432",
            "accountStatus": "ACTIVE",
            "bankBranchAddressDTO": {
                "id": 1,
                "branchName": "Railway station branch",
                "street": "Andhra pradesh",
                "city": "Adoni",
                "state": "Andhra pradesh",
                "zipcode": "518302"
            }
        },
        {
            "accountNumber": "56695716280",
            "accountType": "SAVINGS",
            "createdAt": "2024-10-17T19:36:16.58692",
            "accountStatus": "ACTIVE",
            "bankBranchAddressDTO": {
                "id": 1,
                "branchName": "Railway station branch",
                "street": "Andhra pradesh",
                "city": "Adoni",
                "state": "Andhra pradesh",
                "zipcode": "518302"
            }
        },
        {
            "accountNumber": "53067839593",
            "accountType": "SAVINGS",
            "createdAt": "2024-10-17T19:38:19.808999",
            "accountStatus": "ACTIVE",
            "bankBranchAddressDTO": {
                "id": 1,
                "branchName": "Railway station branch",
                "street": "Andhra pradesh",
                "city": "Adoni",
                "state": "Andhra pradesh",
                "zipcode": "518302"
            }
        },
        {
            "accountNumber": "66813123704",
            "accountType": "SAVINGS",
            "createdAt": "2024-10-17T21:19:20.836768",
            "accountStatus": "ACTIVE",
            "bankBranchAddressDTO": {
                "id": 1,
                "branchName": "Railway station branch",
                "street": "Andhra pradesh",
                "city": "Adoni",
                "state": "Andhra pradesh",
                "zipcode": "518302"
            }
        }
    ]
    ```

### 4. Delete Account
- **Endpoint:** `/api/account/deactivateAccount`
- **Method:** `DELETE`
- **Request Body:**
    ```json
    {
        "accountNumber": "482928338448"
    }
    ```
- **Response:** "Successful" or "Failure".
 

 # Work In Progress

 ## Testing Checklist:
 - Create seperate package to handle exception. **Status** Incomplete.
 - verify null checks once & break validations in request **Status** Incomplete.

 #### Sensitive Data Encryption Requirements
- Account number (last four digits should not be encrypted).
- pin.