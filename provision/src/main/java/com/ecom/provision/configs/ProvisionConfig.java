package com.ecom.provision.configs;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import com.ecom.provision.prov.MultiTenantProvider;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@ComponentScan("com.ecom.provision")
public class ProvisionConfig {

    @Value("${provision.multitenancy.entity-package}")
    private String entityPackage;

    @Bean
    @ConditionalOnProperty(name = "provision.multitenancy.enabled", havingValue = "true")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            DataSource dataSource,
            MultiTenantProvider connectionProvider,
            CurrentTenantIdentifierResolver<String> tenantResolver) {
        System.out.println("Configuring multi-tenant EntityManagerFactory with SCHEMA strategy");

        Map<String, Object> props = new HashMap<>();
        // Strategy must be a string in Hibernate 7.x
        props.put("hibernate.multiTenancy", "SCHEMA");
        // Provider and resolver must use string keys
        props.put("hibernate.multi_tenant_connection_provider", connectionProvider);
        props.put("hibernate.tenant_identifier_resolver", tenantResolver);

        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setJpaPropertyMap(props);

        em.setPackagesToScan(entityPackage);
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        return em;
    }


}
