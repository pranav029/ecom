package com.ecom.provision.exception;

import com.ecom.core.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ProvisionExceptionHandler {

    @ExceptionHandler(TenantProvisioningException.class)
    public ResponseEntity<?> handleProvisionException(TenantProvisioningException e) {
        return ApiResponse.failureResponseEntity(null, List.of(e.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
