package com.ecom.order;

import com.ecom.core.constants.KafkaTopics;
import com.ecom.core.eventTypes.ProvisionEventType;
import com.ecom.core.events.ProvisionEvent;
import com.ecom.provision.dto.ProvisionRequest;
import com.ecom.provision.service.ProvisionService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderProvisionService {
    private final ProvisionService provisionService;

    @KafkaListener(topics = KafkaTopics.TENANT_PROVISION_TOPIC, groupId = KafkaTopics.ECOM_CONSUMER_GROUP)
    public void provisionEventHandler(ProvisionEvent provisionEvent) {
        System.out.println("Received provision event for tenant: " + provisionEvent.tenantId());

        if (provisionEvent.type().equals(ProvisionEventType.TENANT_CREATED)) {
            ProvisionRequest request = ProvisionRequest.builder()
                    .tenantId(provisionEvent.tenantId())
                    .companyName(provisionEvent.companyName())
                    .companyCode(provisionEvent.companyCode())
                    .build();

            provisionService.createTenant(request);
        }
    }
}
