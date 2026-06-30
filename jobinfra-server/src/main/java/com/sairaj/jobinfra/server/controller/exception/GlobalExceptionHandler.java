package com.sairaj.jobinfra.server.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<com.sairaj.jobinfra.server.controller.dto.ApiResponse<Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        StringBuilder errorMessage = new StringBuilder();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errorMessage.append(fieldName).append(" ").append(message).append("; ");
        });
        
        return ResponseEntity.badRequest().body(com.sairaj.jobinfra.server.controller.dto.ApiResponse.error(
                "VALIDATION_ERROR", errorMessage.toString()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<com.sairaj.jobinfra.server.controller.dto.ApiResponse<Object>> handleAllExceptions(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(com.sairaj.jobinfra.server.controller.dto.ApiResponse.error(
                        "INTERNAL_ERROR", ex.getMessage()));
    }
}
