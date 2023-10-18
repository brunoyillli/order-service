package io.github.brunoyillli.orderservice.config.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionGlobalHandler {

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<?> handleValidationException(ValidationException validationException){
        ExceptionDetails details = new ExceptionDetails(HttpStatus.BAD_REQUEST.value(), validationException.getMessage());
        return new ResponseEntity<>(details, HttpStatus.BAD_REQUEST);
    }
}