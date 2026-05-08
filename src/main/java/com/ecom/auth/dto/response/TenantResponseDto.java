package com.ecom.auth.dto.response;

import com.ecom.auth.entities.TenantStatus;
import lombok.Data;

@Data
public class TenantResponseDto {
    private String id;
    private String companyName;
    private String companyCode;
    private TenantStatus status;
}

