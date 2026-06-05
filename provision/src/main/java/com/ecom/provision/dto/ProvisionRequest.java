package com.ecom.provision.dto;

import lombok.Builder;

@Builder
public record ProvisionRequest(
        String tenantId,
        String companyName,
        String companyCode
) {
}
