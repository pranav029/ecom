package com.ecom.auth.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

//@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "tenants")
public class Tenant extends AbstractEntity {
    @Column(name = "company_name", nullable = false)
    private String companyName;

    @Column(name = "company_code", nullable = false)
    private String companyCode;

    @Column(name = "status", nullable = false)
    private TenantStatus status = TenantStatus.PENDING;

    @OneToMany(mappedBy = "tenant", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<User> users = new HashSet<>();


    /*for Onboarding flow*/

    @Column(name = "admin_username", nullable = false)
    private String adminUsername;

    @Column(name = "admin_email", nullable = false)
    private String adminEmail;

    @Column(name = "admin_password", nullable = false)
    private String adminPassword;

    @Column(name = "admin_first_name", nullable = false)
    private String adminFirstName;

    @Column(name = "admin_last_name", nullable = false)
    private String adminLastName;
}
