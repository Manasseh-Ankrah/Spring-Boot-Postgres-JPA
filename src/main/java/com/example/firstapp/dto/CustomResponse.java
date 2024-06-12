package com.example.firstapp.dto;


import com.example.firstapp.auth.Auth;

public class CustomResponse {
    String message;
    Auth result;
    public CustomResponse( Auth result,String message) {
        this.result = result;
        this.message = message;

        System.out.println("user => " + result + "Message => " + message);
    }



    public void setMessage(String message) {
        this.message = message;

    }

    public String getMessage() {
        return message;
    }

    public void setResult(Auth result) {
        this.result = result;
    }

    public Auth getResult() {
        return result;
    }
}
