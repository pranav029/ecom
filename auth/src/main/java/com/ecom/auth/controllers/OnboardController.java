package com.ecom.auth.controllers;

import com.ecom.auth.dto.request.RegisterTenantRequest;
import com.ecom.auth.services.TenantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/onboarding")
public class OnboardController {
    private final TenantService tenantService;

    @PostMapping
    public ResponseEntity<Void> registerTenant(
            @RequestBody RegisterTenantRequest request
    ) {
        tenantService.registerTenant(request);
        return ResponseEntity.ok().build();
    }
}
