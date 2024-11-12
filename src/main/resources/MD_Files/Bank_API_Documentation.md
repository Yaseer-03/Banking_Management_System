
# Bank API Documentation

This documentation outlines the available endpoints for managing bank records, including adding, retrieving, and deleting bank details.

## Bank Endpoints

### 1. Add Bank
- **Endpoint:** `/api/bank/addbank`
- **Method:** `POST`
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
- **Method:** `GET`
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
- **Method:** `GET`
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
- **Method:** `DELETE`
- **Request Body:**
    ```json
    {
        "id": "1"
    }
    ```
- **Response:** `"Successful"` or `"Failure"`


## **Handled by Super Admin**

## Need to implement
- Develop ifsc code for individual branch 
