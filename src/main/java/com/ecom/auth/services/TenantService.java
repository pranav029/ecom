package com.ecom.auth.services;

import com.ecom.auth.dto.TenantRequestDto;
import com.ecom.auth.dto.TenantResponseDto;
import com.ecom.auth.entities.Tenant;
import com.ecom.auth.repositories.TenantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TenantService {
    private final TenantRepository tenantRepository;

    public List<Tenant> findAll() {
        return tenantRepository.findAll();
    }

    public Optional<Tenant> findById(String id) {
        return tenantRepository.findById(id);
    }

    public Tenant save(Tenant tenant) {
        return tenantRepository.save(tenant);
    }

    public void deleteById(String id) {
        tenantRepository.deleteById(id);
    }

    public TenantResponseDto toResponseDto(Tenant tenant) {
        TenantResponseDto dto = new TenantResponseDto();
        dto.setId(tenant.getId());
        dto.setCompanyName(tenant.getCompanyName());
        dto.setCompanyCode(tenant.getCompanyCode());
        dto.setStatus(tenant.getStatus());
        return dto;
    }

    public Tenant fromRequestDto(TenantRequestDto dto) {
        Tenant tenant = new Tenant();
        tenant.setCompanyName(dto.getCompanyName());
        tenant.setCompanyCode(dto.getCompanyCode());
        tenant.setStatus(dto.getStatus());
        return tenant;
    }
}
