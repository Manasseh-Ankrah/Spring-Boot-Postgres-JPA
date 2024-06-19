package com.example.firstapp.dto;



public class CustomResponse<T> {
    String message;
    T result;
    public CustomResponse(T result,String message) {
        this.result = result;
        this.message = message;
    }



    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public T getResult() {
        return result;
    }
}
