package com.ecom.auth.repositories;

import com.ecom.auth.entities.Tenant;
import com.ecom.auth.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByTenantAndUsername(Tenant tenant, String username);
}

