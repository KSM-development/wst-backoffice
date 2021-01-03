package com.ksm.wstbackoffice.controller.handler;

import com.ksm.wstbackoffice.exception.ErrorResponseMessage;
import com.ksm.wstbackoffice.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDate;

@ControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseMessage> resourceNotFoundException(ResourceNotFoundException ex) {
        ErrorResponseMessage message = new ErrorResponseMessage(
                LocalDate.now(),
                ex.getMessage());

        return new ResponseEntity<ErrorResponseMessage>(message, HttpStatus.NOT_FOUND);
    }
}
