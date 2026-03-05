package com.rameel.To_Do_List_API.controller;

import com.rameel.To_Do_List_API.exception.EmailAlreadyExistsException;
import com.rameel.To_Do_List_API.exception.InvalidPassword;
import com.rameel.To_Do_List_API.exception.UnknownUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<String> handleEmailExists(EmailAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(ex.getMessage());
    }

    @ExceptionHandler(UnknownUser.class)
    public ResponseEntity<String> handleUnknownUserException(UnknownUser ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }

    @ExceptionHandler(InvalidPassword.class)
    public ResponseEntity<String> handleInvalidPasswordException(InvalidPassword ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(ex.getMessage());
    }
}
