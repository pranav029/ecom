package com.ecom.auth.entities;

import com.ecom.core.entities.AbstractEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "provision")
public class Provision extends AbstractEntity {
    @Column(name = "tenant_id", nullable = false)
    private String tenantId;

    @Column(name = "company_code", nullable = false)
    private String companyCode;

    @Column(name = "company_name", nullable = false)
    private String companyName;

    @Enumerated(EnumType.STRING)
    @Column(name = "service_type", nullable = false)
    private ServiceType serviceType;

    @Enumerated(EnumType.STRING)
    @Column(name = "state", nullable = false)
    private ProvisionState state = ProvisionState.PENDING;
}
