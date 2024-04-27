package com.example.SmartKitchen.util;

public class PasswordsNotMatchException extends RuntimeException{
    public PasswordsNotMatchException(String message) {
        super(message);
    }
}
