package com.ecom.auth.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
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
}
