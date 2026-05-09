package com.ecom.auth.repositories;

import com.ecom.auth.entities.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface TenantRepository extends JpaRepository<Tenant, String> {
    Optional<Tenant> findByCompanyName(String companyName);

    Optional<Tenant> findByCompanyCode(String companyCode);
}

