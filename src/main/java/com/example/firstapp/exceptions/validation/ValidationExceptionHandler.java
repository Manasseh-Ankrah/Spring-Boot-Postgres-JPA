package com.example.firstapp.exceptions.validation;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ValidationExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(value = {ApiRequestException.class})
    @ExceptionHandler(value = {MethodArgumentNotValidException.class,})



    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;

        List<String> messages = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach((error) ->{
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            messages.add(errorMessage);
        });

                ValidationException validationException = new ValidationException(
                        badRequest.value(),
                        badRequest,
                        messages,
                ZonedDateTime.now(ZoneId.of("Z"))
        );




        return  new ResponseEntity<Object>(validationException, badRequest );

    };
}
















//@ControllerAdvice
//public class ValidationExceptionHandler {
//
//        @ExceptionHandler(value = {ApiRequestException.class})
//
//    public ResponseEntity<Object> handleApiRequestException(ApiRequestException e) {
////        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
//        HttpStatus httpStatus = e.getStatus();
//        int code = e.getCode();
//
//        ValidationException validationException = new ValidationException(
//                e.getMessage(),
//                code,
//                httpStatus,
//                ZonedDateTime.now(ZoneId.of("Z"))
//        );
//
//        return new ResponseEntity<>(validationException, httpStatus);
//    };
//}
