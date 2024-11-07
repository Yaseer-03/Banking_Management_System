package com.example.BankingManagementSystem.Dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseWrapper<T> {
    private T data;
    private String message;

    public ResponseWrapper(T data, String message) {
        this.data = data;
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public boolean hasError() {
        return message != null;
    }
}

