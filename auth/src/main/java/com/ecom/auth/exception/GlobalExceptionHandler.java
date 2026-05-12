package com.ecom.auth.exception;

import com.ecom.core.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RequestException.class)
    public ResponseEntity<String> handleResourceNotFoundException(RequestException ex) {
        return ResponseEntity.status(ex.getStatus()).body(ex.getMessage());
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<String> handleResourceNotFoundException(BusinessException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<?> handleOptionalError(NoSuchElementException ex) {
        return ApiResponse.failureResponseEntity("Resource not found", List.of(ex.getMessage()), HttpStatus.NOT_FOUND);
    }
}
