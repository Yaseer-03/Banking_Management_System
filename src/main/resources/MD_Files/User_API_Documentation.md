
# User API Documentation

This documentation outlines the available endpoints for managing user accounts, including login, registration, retrieval, update, and deletion.

## User Endpoints

### 1. User Login
- **Endpoint:** `/api/user/login`
- **Method:** `POST`
- **Request Body:**
    ```json
    {
        "mPIN": "1234"
    }
    ```
- **Response:** Returns an authorization key and the user ID.
    ```json
    {
        "Authorization": "Bearer generated_key",
        "LoginUserId": "1"
    }
    ```

### 2. User Onboarding (Sign Up)

#### Personal Details
- **Endpoint:** `/api/user/signup/personalDetails`
- **Method:** `POST`
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
- **Response:** Returns user details upon successful registration.
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

#### Address Details
- **Endpoint:** `/api/user/signup/addressDetails`
- **Method:** `POST`
- **Request Parameter:** `userId` (generated upon registration)
    - Example: `userId=1`
- **Request Body:**
    ```json
    {
        "street": "123 Main St",
        "city": "City",
        "state": "State",
        "zipcode": "123456"
    }
    ```
- **Response:** `"User registered successfully"`

### 3. Get User by ID
- **Endpoint:** `/api/user/getuser/{userId}`
- **Method:** `GET`
- **Response:** Returns the user DTO object if found, or an appropriate message if not.
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
- **Method:** `GET`
- **Response:** Returns a list of all users.
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
- **Method:** `PUT`
- **Request Body:**
    ```json
    {
        "mobileNumber": "1234567890",
        "email": "john.doe@example.com"
    }
    ```
- **Response:** Returns updated user details.
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
### 6. Update Mpin
- **Endpoint:** `/api/user/updateMpin?{mobileNumber}`
- **Method:** `POST`
- **Request Body:**
    ```json
    {
        "mPIN": "1234"
    }
    ```
- **Response:** `"Mpin Updated Successful"` or `"The provided MPIN is the same as the current one."`

### 7. Delete User
- **Endpoint:** `/api/user/deleteuser`
- **Method:** `DELETE`
- **Request Body:**
    ```json
    {
        "userId": "1"
    }
    ```
- **Response:** `"Successful"` or `"Failure"`

## Work In Progress

## Need To Implement (Updated: 12-11-2024)

- User registration with null values (check individual properties). **Status:** Done.
- User registration by breaking validations (check individual properties). **Status:** Done.
- User registration with existing email and mobile number. **Status:** Done.
- Parse error handling for date of birth input (DD-MM-YYYY to YYYY-MM-DD). **Status:** Done.
- After successfully complete's his registration (after mpin setup ) send a response class which includes user personal details and user address details in it.  **Status** Done.
- Retrieve all users and by user ID (valid and invalid). **Status:** Done.
- Aadhar card number must be unique. **Status:** Done.
- In user address details if the user id exists already then user has to get response as user address already exist. **Status** Incomplete.
- While updating mpin ( utilize the mpin creation method only for updating Use method type put for updating and redirect to the setting mpin existed method ) **Status** Incomplete.
- While setting mpin or updating mpin use mobile of the user instead of using mobile number **Status** Incomplete.
- While retriving user details show everything related to the such as address and exclude those properties or show the properties as xxxx which are encrypted (such as pin and mpin) **Status** Incomplete.
- Login user with mPIN and mobile number. **Status** Incomplete.
- Update mPIN requires mobile number verification. **Status** Incomplete.
- Create seperate package to handle exception. **Status** Incomplete.
- If user deletes his profile details then the user account and other such as loan, balance entities has to be deleted, if user has account and other such as loan, balance entities these should be deleted first and then only user can delete his profile. **Status** Incomplete.
- While retriving all user's, use reactive programming ( Get the user's data which is fetched first and vice versa..) **Status** Incomplete.

### Sensitive Data Encryption Requirements

**Encrypt Sensitive Details**:
   - **User Entity**:
     - Aadhar Card: Encrypt all but the last four digits.
     - Mobile Number: Encrypt all but the last four digits (optional).
     - MPIN: Fully encrypt.
