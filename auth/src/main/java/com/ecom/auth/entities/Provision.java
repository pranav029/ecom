package com.ecom.auth.entities;

import com.ecom.core.entities.AbstractEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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
    private String tenantId;
    private String companyCode;
    private String companyName;
    private
    private ProvisionState state;
}
