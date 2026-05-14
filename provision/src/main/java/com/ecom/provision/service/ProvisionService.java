package com.ecom.provision.service;

import com.ecom.provision.dto.ProvisionRequest;
import com.ecom.provision.exception.TenantProvisioningException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.flywaydb.core.Flyway;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

@Service
@Slf4j
@RequiredArgsConstructor
public final class ProvisionService {
    private final JdbcTemplate jdbcTemplate;
    private final DataSource dataSource;

    public void createTenant(ProvisionRequest request) {
        final String schemaName = "tenant_" + request.companyCode().toLowerCase();

        try {
            log.info("Provisioning tenant: {} (schema: {})", request.companyName(), schemaName);

            createTenantSchema(schemaName);
            log.info("Schema created successfully: {}", schemaName);


            runTenantMigrations(schemaName);
            log.info("Tenant migrations completed successfully for schema: {}", schemaName);

        } catch (final Exception e) {
            log.error("Failed to provision tenant: {}", request.companyName(), e);

            // rollback: drop schema creation
            try {
                dropTenantSchema(schemaName);
            } catch (final Exception exp) {
                log.error("Failed to rollback schema creation for tenant: {}", request.companyName(), e);
            }
            throw new TenantProvisioningException("Failed to provision tenant");
        }

    }

    private void createTenantSchema(String schema) {
        String sql = String.format("CREATE SCHEMA IF NOT EXISTS %s", schema);
        jdbcTemplate.execute(sql);
    }

    private void dropTenantSchema(final String schemaName) {
        final String sql = String.format("DROP SCHEMA IF EXISTS %s CASCADE", schemaName);
        this.jdbcTemplate.execute(sql);
    }

    private void runTenantMigrations(String schemaName) {
        final Flyway tenantFlyway = Flyway.configure()
                .dataSource(this.dataSource)
                .schemas(schemaName)
                .locations("classpath:db/migration/tenant")
                .baselineOnMigrate(true)
                .table("flyway_schema_history")
                .validateOnMigrate(true)
                .cleanDisabled(true)
                .load();

        log.info("Tenant migrations started");
        tenantFlyway.migrate();
        log.info("Tenant migrations completed");
    }

}
