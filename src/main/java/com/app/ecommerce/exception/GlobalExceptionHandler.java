package com.app.ecommerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException e) {
        return new ResponseEntity<>(
                e.getMessage(), HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(ResourceDuplicationException.class)
    public ResponseEntity<String> handleResourceDuplicationException(ResourceDuplicationException e) {
        return new ResponseEntity<>(
                e.getMessage(), HttpStatus.CONFLICT
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGlobalException(Exception e) {
        return new ResponseEntity<>(
                e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler(AuthenticationFailedException.class)
    public ResponseEntity<String> handleAuthenticationFailedException(AuthenticationFailedException e) {
        return new ResponseEntity<>(
                e.getMessage(), HttpStatus.UNAUTHORIZED
        );
    }
}
