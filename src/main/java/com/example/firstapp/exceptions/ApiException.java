package com.example.firstapp.exceptions;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public class ApiException {
    private final String message;

    private final HttpStatus httpStatus;
    private final int code;

    private final ZonedDateTime timestamp;

    public ApiException(String message,int code, HttpStatus httpStatus, ZonedDateTime timestamp) {
        this.message = message;
        this.code = code;
        this.httpStatus = httpStatus;
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public int getCode() {
        return code;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }
}
