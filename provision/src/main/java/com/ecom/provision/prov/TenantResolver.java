package com.ecom.provision.prov;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "provision.multitenancy.enabled", havingValue = "true")
public class TenantResolver implements CurrentTenantIdentifierResolver<String> {
    @Override
    public String resolveCurrentTenantIdentifier() {
        return "public";
    }

    @Override
    public boolean validateExistingCurrentSessions() {
        return true;
    }
}
