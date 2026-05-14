package com.ecom.provision.multitenancy;

import com.ecom.provision.context.TenantContextHolder;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "provision.multitenancy.enabled", havingValue = "true")
public class TenantResolver implements CurrentTenantIdentifierResolver<String> {
    private static final String PUBLIC_SCHEMA = "public";

    @Override
    public String resolveCurrentTenantIdentifier() {
        String schema = TenantContextHolder.getTenant();
        System.out.println("Reading schema");
        System.out.println(schema);
        if (schema != null) {
            return String.format("tenant_%s", schema);
        }

        return PUBLIC_SCHEMA;
    }

    @Override
    public boolean validateExistingCurrentSessions() {
        return true;
    }
}
