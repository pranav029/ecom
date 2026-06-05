package com.ecom.core.events;

import com.ecom.core.eventTypes.ProvisionEventType;
import lombok.Builder;

@Builder
public record ProvisionEvent(
        ProvisionEventType type,
        String tenantId,
        String companyName,
        String companyCode
) {
}
