package com.group4.demo.advices;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> resourceNotFoundException(ResourceNotFoundException ex, WebRequest rq) {
        ErrorDetails err = new ErrorDetails(LocalDateTime.now(), ex.getMessage(), rq.getDescription(false));
        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CouldNotBeUpdatedException.class)
    public ResponseEntity<ErrorDetails> couldNotBeUpdatedException(CouldNotBeUpdatedException ex, WebRequest rq) {
        ErrorDetails err = new ErrorDetails(LocalDateTime.now(), ex.getMessage(), rq.getDescription(false));
        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AuthenticationFailedException.class)
    public ResponseEntity<ErrorDetails> authenticationFailedException(AuthenticationFailedException ex, WebRequest rq) {
        ErrorDetails err = new ErrorDetails(LocalDateTime.now(), ex.getMessage(), rq.getDescription(false));
        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> globalExceptions(Exception ex, WebRequest rq) {
        ErrorDetails err = new ErrorDetails(LocalDateTime.now(), ex.getMessage(), rq.getDescription(false));
        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        Map<String,String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error)->{
            String fieldname = ((FieldError)error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldname,message);
        });
        return  new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
    }
}

