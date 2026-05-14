package com.ecom.provision.controllers;

import com.ecom.core.dto.ApiResponse;
import com.ecom.provision.dto.ProvisionRequest;
import com.ecom.provision.service.ProvisionService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@ConditionalOnProperty(name = "provision.api.enabled", havingValue = "true")
@RequestMapping("/api/${provision.api.identifier:tenant}/provision")
public class ProvisionController {
    private final ProvisionService provisionService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<Void> provisionTenant(@RequestBody ProvisionRequest request) {
        provisionService.createTenant(request);
        return ApiResponse.success("Tenant provisioned successfully", null);
    }
}
