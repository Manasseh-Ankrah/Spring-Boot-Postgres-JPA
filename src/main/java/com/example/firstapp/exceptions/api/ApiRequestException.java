package com.example.firstapp.exceptions.api;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;

public class ApiRequestException extends RuntimeException{
//    private String message;


    public ApiRequestException(String message) {
        super(message);
    }


    public ApiRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
