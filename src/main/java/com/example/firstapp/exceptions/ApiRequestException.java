package com.example.firstapp.exceptions;

import org.springframework.http.HttpStatus;

public class ApiRequestException extends RuntimeException{
    private HttpStatus status;
    private int code;

    public ApiRequestException(String message,int code, HttpStatus status) {
        super(message);
        this.code = code;
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public int getCode() {
        return code;
    }

    public ApiRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
