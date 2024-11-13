package com.example.BankingManagementSystem.ResponseStatusCode;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.example.BankingManagementSystem.Dto.ResponseWrapper;

@Component
public class ResponseService {

    // Utility method to return a successful response (200 OK)
    public <T> ResponseEntity<ResponseWrapper<T>> createSuccessResponse(T data) {
        ResponseWrapper<T> responseWrapper = new ResponseWrapper<>(data, null);
        return new ResponseEntity<>(responseWrapper, HttpStatus.OK);
    }

    // Utility method to return a created response (201 Created)
    public <T> ResponseEntity<ResponseWrapper<T>> createCreatedResponse(T data) {
        ResponseWrapper<T> responseWrapper = new ResponseWrapper<>(data, null);
        return new ResponseEntity<>(responseWrapper, HttpStatus.CREATED);
    }

    // Utility method to return a no content response (204 No Content)
    public <T> ResponseEntity<ResponseWrapper<T>> createNoContentResponse() {
        ResponseWrapper<T> responseWrapper = new ResponseWrapper<>(null, null);
        return new ResponseEntity<>(responseWrapper, HttpStatus.NO_CONTENT);
    }

    // Utility method to return an error response with a message (400 Bad Request)
    public <T> ResponseEntity<ResponseWrapper<T>> createErrorResponse(String errorMessage) {
        ResponseWrapper<T> responseWrapper = new ResponseWrapper<>(null, errorMessage);
        return new ResponseEntity<>(responseWrapper, HttpStatus.BAD_REQUEST);
    }

    // Utility method to return a resource not found response (404 Not Found)
    public <T> ResponseEntity<ResponseWrapper<T>> createNotFoundResponse(String errorMessage) {
        ResponseWrapper<T> responseWrapper = new ResponseWrapper<>(null, errorMessage);
        return new ResponseEntity<>(responseWrapper, HttpStatus.NOT_FOUND);
    }

    // Utility method to return a conflict response (409 Conflict)
    public <T> ResponseEntity<ResponseWrapper<T>> createConflictResponse(String errorMessage) {
        ResponseWrapper<T> responseWrapper = new ResponseWrapper<>(null, errorMessage);
        return new ResponseEntity<>(responseWrapper, HttpStatus.CONFLICT);
    }

    // Utility method to return a method not allowed response (405 Method Not
    // Allowed)
    public <T> ResponseEntity<ResponseWrapper<T>> createMethodNotAllowedResponse(String errorMessage) {
        ResponseWrapper<T> responseWrapper = new ResponseWrapper<>(null, errorMessage);
        return new ResponseEntity<>(responseWrapper, HttpStatus.METHOD_NOT_ALLOWED);
    }

    // Utility method to return an internal server error response (500 Internal
    // Server Error)
    public <T> ResponseEntity<ResponseWrapper<T>> createInternalServerErrorResponse(String errorMessage) {
        ResponseWrapper<T> responseWrapper = new ResponseWrapper<>(null, errorMessage);
        return new ResponseEntity<>(responseWrapper, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Utility method to return an unprocessable entity response (422 Unprocessable
    // Entity)
    public <T> ResponseEntity<ResponseWrapper<T>> createUnprocessableEntityResponse(String errorMessage) {
        ResponseWrapper<T> responseWrapper = new ResponseWrapper<>(null, errorMessage);
        return new ResponseEntity<>(responseWrapper, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    // Utility method to return an accepted response (202 Accepted) - for async
    // operations
    public <T> ResponseEntity<ResponseWrapper<T>> createAcceptedResponse(T data) {
        ResponseWrapper<T> responseWrapper = new ResponseWrapper<>(data, null);
        return new ResponseEntity<>(responseWrapper, HttpStatus.ACCEPTED);
    }
}
