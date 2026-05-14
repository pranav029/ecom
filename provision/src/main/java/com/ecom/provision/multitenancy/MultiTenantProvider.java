package com.ecom.provision.multitenancy;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Component
@Slf4j
@ConditionalOnProperty(name = "provision.multitenancy.enabled", havingValue = "true")
public class MultiTenantProvider implements MultiTenantConnectionProvider<String> {
    private final DataSource dataSource;

    public MultiTenantProvider(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Override
    public Connection getAnyConnection() throws SQLException {
        return dataSource.getConnection();
    }

    @Override
    public void releaseAnyConnection(Connection connection) throws SQLException {
        connection.close();
    }

    @Override
    public Connection getConnection(String tenantIdentifier) throws SQLException {
        log.debug("Getting connection for tenant: {}", tenantIdentifier);
        final Connection connection = getAnyConnection();
        try {
            if (tenantIdentifier != null && !tenantIdentifier.equals("public") ) {
                String sql = String.format("SET search_path TO %s, public",tenantIdentifier);
                connection.createStatement().execute(sql);
                log.trace("Set search_path to: {}", tenantIdentifier);
            }
        } catch (final SQLException e) {
            log.error("Error getting connection for tenant: {}", tenantIdentifier, e);
            throw e;
        }
        return connection;
    }

    @Override
    public void releaseConnection(String tenantIdentifier, Connection connection) throws SQLException {
        try {
            connection.createStatement().execute("SET search_path TO public");
        } catch (final SQLException e) {
            log.error("Error getting connection for tenant: {}", tenantIdentifier, e);
        }
        connection.close();
    }

    @Override
    public boolean supportsAggressiveRelease() {
        return false;
    }

    @Override
    public boolean isUnwrappableAs(Class<?> aClass) {
        return MultiTenantConnectionProvider.class.equals(aClass) ||
                this.getClass().isAssignableFrom(aClass);
    }

    @Override
    public <T> T unwrap(Class<T> aClass) {
        if (isUnwrappableAs(aClass)) {
            return (T) this;
        }
        throw new IllegalArgumentException("Unknown unwrap type: " + aClass);
    }
}
