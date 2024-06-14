package com.example.firstapp.exceptions.api;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;
import java.util.List;

public class ApiException {

    private final HttpStatus httpStatus;
    private final int code;

    private List<String> message;

    private final ZonedDateTime timestamp;

    public ApiException( int code, HttpStatus httpStatus, List<String> message, ZonedDateTime timestamp) {
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
        this.timestamp = timestamp;
    }

    public ApiException( int code, HttpStatus httpStatus, ZonedDateTime timestamp) {
        this.code = code;
        this.httpStatus = httpStatus;
        this.timestamp = timestamp;
    }



    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public List<String> getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }
}
