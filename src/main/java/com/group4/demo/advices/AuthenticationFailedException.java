package com.group4.demo.advices;

public class AuthenticationFailedException extends Exception {
    public AuthenticationFailedException(String message){
        super(message);
    }
}
