package com.example.firstapp.exceptions.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ApiExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = { ApiRequestException.class})




    public ResponseEntity<Object> handleMethodApiException(ApiRequestException e) {

        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
//        HttpStatus httpStatus = e.;
//        int code = e.getCode();
        System.out.println("ERRORS from ApiExceptionHandler ->> " + e);

        List<String> message = List.of(e.getMessage());
        ApiException apiException = new ApiException(
                badRequest.value(),
                badRequest,
                message,
                ZonedDateTime.now(ZoneId.of("Z"))
        );


        return  new ResponseEntity<Object>(apiException, badRequest );

    };
}
















//@ControllerAdvice
//public class ApiExceptionHandler {
//
//        @ExceptionHandler(value = {ApiRequestException.class})
//
//    public ResponseEntity<Object> handleApiRequestException(ApiRequestException e) {
////        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
//        HttpStatus httpStatus = e.getStatus();
//        int code = e.getCode();
//
//        ApiException apiException = new ApiException(
//                e.getMessage(),
//                code,
//                httpStatus,
//                ZonedDateTime.now(ZoneId.of("Z"))
//        );
//
//        return new ResponseEntity<>(apiException, httpStatus);
//    };
//}
