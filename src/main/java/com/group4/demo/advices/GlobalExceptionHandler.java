package com.group4.demo.advices;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> resourceNotFoundException(ResourceNotFoundException ex, WebRequest rq){
        ErrorDetails err = new ErrorDetails(LocalDateTime.now(), ex.getMessage(),rq.getDescription(false));
        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CouldNotBeUpdatedException.class)
    public ResponseEntity<ErrorDetails> couldNotBeUpdatedException(Exception ex, WebRequest rq) {
        ErrorDetails err = new ErrorDetails(LocalDateTime.now(), ex.getMessage(), rq.getDescription(false));
        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> globalExceptions(Exception ex, WebRequest rq){
        ErrorDetails err = new ErrorDetails(LocalDateTime.now(), ex.getMessage(),rq.getDescription(false));
        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
    }


}

