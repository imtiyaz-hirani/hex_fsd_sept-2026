package com.springboot.helpdesk.config;

import com.springboot.helpdesk.exception.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(
            ResourceNotFoundException e
    ){
        //always reply the object not the string

        return ResponseEntity
                .badRequest()
                .body(e.getMessage());
    }
}
