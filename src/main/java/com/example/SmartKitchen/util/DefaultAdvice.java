package com.example.SmartKitchen.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class DefaultAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodNotValid(MethodArgumentNotValidException e) {
        return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).body(e.getBody());
    }

    @ExceptionHandler(PasswordsNotMatchException.class)
    public ResponseEntity<?> handlePasswordsNotMatch(PasswordsNotMatchException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

}