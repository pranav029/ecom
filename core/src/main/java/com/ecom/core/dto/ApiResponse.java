package com.ecom.core.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Data
@AllArgsConstructor
public class ApiResponse<T> {
    private boolean success;
    private String message;
    private T data;
    private List<String> errors;

    public static <T> ApiResponse<T> success(String message, T data) {
        return new ApiResponse<>(true, message, data, null);
    }

    public static <T> ApiResponse<T> failure(String message, List<String> errors) {
        return new ApiResponse<>(false, message, null, errors);
    }

    public static <T> ResponseEntity<ApiResponse<T>> successResponseEntity(String message, T data, HttpStatus status) {
        return ResponseEntity.status(status).body(success(message, data));
    }

    public static <T> ResponseEntity<ApiResponse<T>> failureResponseEntity(String message, List<String> errors, HttpStatus status) {
        return ResponseEntity.status(status).body(failure(message, errors));
    }

}
