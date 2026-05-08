package com.ecom.auth.dto.request;

public record RegisterTenantRequest(
        String companyName,
        String companyCode,
        String adminUsername,
        String adminPassword,
        String adminEmail,
        String adminFirstName,
        String adminLastName) {
}
