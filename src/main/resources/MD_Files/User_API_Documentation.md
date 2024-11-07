
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

### 6. Delete User
- **Endpoint:** `/api/user/deleteuser`
- **Method:** `DELETE`
- **Request Body:**
    ```json
    {
        "userId": "1"
    }
    ```
- **Response:** `"Successful"` or `"Failure"`

### 7. Change mPIN
- **Endpoint:** `/api/user/updateMpin`
- **Method:** `POST`
- **Request Body:**
    ```json
    {
        "mPIN": "1234",
        "newMpin": "4321"
    }
    ```
- **Response:** `"Successful"` or `"Failure"`



## Work In Progress

## Testing Checklist (Updated: 11-10-2024)
- User registration with null values (check individual properties). **Status:** Done.
- User registration by breaking validations (check individual properties). **Status:** Done.
- User registration with existing email and mobile number. **Status:** Done.
- Parse error handling for date of birth input (DD-MM-YYYY to YYYY-MM-DD). **Status:** Done.
- Retrieve all users and by user ID (valid and invalid). **Status:** Done.
- Aadhar card number must be unique. **Status:** Done.
- Login user with mPIN and mobile number. **Status** Incomplete.
- Update mPIN requires mobile number verification. **Status** Incomplete.
- Create seperate package to handle exception. **Status** Incomplete.


### Sensitive Data Encryption Requirements

**Encrypt Sensitive Details**:
   - **User Entity**:
     - Aadhar Card: Encrypt all but the last four digits.
     - Mobile Number: Encrypt all but the last four digits (optional).
     - MPIN: Fully encrypt.
