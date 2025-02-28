package com.payroll.app.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class EmployeePayrollExceptionalHandler {
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, String>> handlingHttpMsgNotReadableException(HttpMessageNotReadableException exception) {
        Map<String, String> errors = new HashMap<>();
        errors.put("Exception", exception.getMessage());
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handlingMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getFieldErrors().
                forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmployeePayrollException.class)
    public ResponseEntity<Map<String, String>> handlingEmployeePayrollException(EmployeePayrollException exception) {
        Map<String, String> errors = new HashMap<>();
        errors.put("Exception", exception.getMessage());

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
