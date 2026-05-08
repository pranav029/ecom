package com.ecom.auth.dto.request;

import lombok.Data;

@Data
public class TenantUpdateRequestDto {
    private String companyName;
    private String companyCode;
}

