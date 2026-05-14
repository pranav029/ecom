package com.ecom.provision.multitenancy;

import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Component
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
        Connection connection = getAnyConnection();
        try {
            connection.setSchema(tenantIdentifier); // switch schema dynamically
        } catch (SQLException e) {
            throw new RuntimeException("Could not alter schema to " + tenantIdentifier, e);
        }
        return connection;
    }

    @Override
    public void releaseConnection(String tenantIdentifier, Connection connection) throws SQLException {
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
