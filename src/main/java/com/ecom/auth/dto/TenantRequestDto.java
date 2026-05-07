package com.ecom.auth.dto;

import com.ecom.auth.entities.TenantStatus;
import lombok.Data;

@Data
public class TenantRequestDto {
    private String companyName;
    private String companyCode;
    private TenantStatus status;
}

