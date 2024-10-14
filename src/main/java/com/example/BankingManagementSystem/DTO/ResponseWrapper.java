package com.example.BankingManagementSystem.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseWrapper<T> {
    private T data;
    private String errorMessage;

    public ResponseWrapper(T data, String errorMessage) {
        this.data = data;
        this.errorMessage = errorMessage;
    }

    public T getData() {
        return data;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public boolean hasError() {
        return errorMessage != null;
    }
}

