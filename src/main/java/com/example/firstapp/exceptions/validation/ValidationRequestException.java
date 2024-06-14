package com.example.firstapp.exceptions.validation;

public class ValidationRequestException extends RuntimeException{


    public ValidationRequestException(String message) {
        super(message);
    }


    public ValidationRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
