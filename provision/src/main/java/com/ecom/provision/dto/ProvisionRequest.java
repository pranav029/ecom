package com.ecom.provision.dto;

public record ProvisionRequest(
        String tenantId,
        String companyName,
        String companyCode
) {
}
