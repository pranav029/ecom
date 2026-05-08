package com.ecom.auth.common.mappers;

import com.ecom.auth.dto.request.RegisterTenantRequest;
import com.ecom.auth.dto.request.TenantUpdateRequestDto;
import com.ecom.auth.dto.response.TenantResponseDto;
import com.ecom.auth.entities.Tenant;
import com.ecom.auth.entities.TenantStatus;

public class MapperUtil {

    public static TenantResponseDto toResponseDto(Tenant tenant) {
        TenantResponseDto dto = new TenantResponseDto();
        dto.setId(tenant.getId());
        dto.setCompanyName(tenant.getCompanyName());
        dto.setCompanyCode(tenant.getCompanyCode());
        dto.setStatus(tenant.getStatus());
        return dto;
    }

    public static Tenant fromRequestDto(TenantUpdateRequestDto dto) {
        Tenant tenant = new Tenant();
        tenant.setCompanyName(dto.getCompanyName());
        tenant.setCompanyCode(dto.getCompanyCode());
        return tenant;
    }

    public static Tenant toTenant(RegisterTenantRequest request) {
        Tenant tenant = new Tenant();
        tenant.setCompanyName(request.companyName());
        tenant.setCompanyCode(request.companyCode());
        tenant.setAdminEmail(request.adminEmail());
        tenant.setAdminFirstName(request.adminFirstName());
        tenant.setAdminLastName(request.adminLastName());
        tenant.setAdminUsername(request.adminUsername());
        tenant.setAdminPassword(request.adminPassword());
        tenant.setStatus(TenantStatus.PENDING);
        return tenant;
    }
}
