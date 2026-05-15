package com.ecom.auth.services;

import com.ecom.auth.common.mappers.MapperUtil;
import com.ecom.auth.dto.request.RegisterTenantRequest;
import com.ecom.auth.dto.request.TenantUpdateRequestDto;
import com.ecom.auth.dto.response.TenantResponseDto;
import com.ecom.auth.entities.Tenant;
import com.ecom.auth.entities.TenantStatus;
import com.ecom.auth.entities.User;
import com.ecom.auth.entities.UserRole;
import com.ecom.core.exception.BusinessException;
import com.ecom.core.exception.RequestException;
import com.ecom.auth.repositories.TenantRepository;
import com.ecom.auth.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TenantService {
    private final TenantRepository tenantRepository;
    private final UserRepository userRepository;

    public void registerTenant(RegisterTenantRequest request) {
        Tenant tenant = MapperUtil.toTenant(request);

        checkIfTenantNameAndCodeAlreadyExists(tenant.getCompanyName(), tenant.getCompanyCode());

        tenantRepository.save(tenant);
    }

    @Transactional
    public void approveTenant(String tenantId) {
        Tenant tenant = tenantRepository.findById(tenantId)
                .orElseThrow(() -> new RequestException("Tenant not found"));

        checkIfTenantAlreadyActive(tenant);
        checkIfTenantProvisioning(tenant);

        if (tenant.getStatus().equals(TenantStatus.SUSPENDED)) {
            tenant.setStatus(TenantStatus.ACTIVE);
            tenantRepository.save(tenant);

            return;
        }

        if (tenant.getStatus().equals(TenantStatus.PENDING)) {
            tenant.setStatus(TenantStatus.PROVISIONING);
            tenantRepository.save(tenant);

            //TRIGGER KAFKA EVENT TO PROVISION ALL SERVICES

            createAdminUserForTenant(tenant);
        }
    }

    public TenantResponseDto update(String tenantId, TenantUpdateRequestDto tenant) {
        Tenant existingTenant = tenantRepository.findById(tenantId)
                .orElseThrow(() -> new RequestException("Tenant not found"));

        checkIfTenantNameAndCodeAlreadyExists(tenant.getCompanyName(), tenant.getCompanyCode());

        existingTenant.setCompanyName(tenant.getCompanyName());
        existingTenant.setCompanyCode(tenant.getCompanyCode());

        return MapperUtil.toResponseDto(tenantRepository.save(existingTenant));
    }

    public List<Tenant> findAll() {
        return tenantRepository.findAll();
    }

    public Optional<Tenant> findById(String id) {
        return tenantRepository.findById(id);
    }

    public void deleteById(String id) {
        tenantRepository.deleteById(id);
    }

    private void createAdminUserForTenant(Tenant tenant) {
        checkIfUserAlreadyRegisteredWithTenant(tenant);

        User user = User.builder()
                .tenant(tenant)
                .username(tenant.getAdminUsername())
                .email(tenant.getAdminEmail())
                .password(tenant.getAdminPassword())
                .firstName(tenant.getAdminFirstName())
                .lastName(tenant.getAdminLastName())
                .role(UserRole.ROLE_TENANT_ADMIN)
                .build();

        userRepository.save(user);
    }

    private void checkIfUserAlreadyRegisteredWithTenant(Tenant tenant) {
        Optional<User> user = userRepository.findByTenantAndUsername(tenant, tenant.getAdminUsername());

        if (user.isPresent())
            throw new BusinessException("A user is already registered with this tenant");
    }

    private void checkIfTenantAlreadyActive(Tenant tenant) {
        if (tenant.getStatus().equals(TenantStatus.ACTIVE))
            throw new BusinessException("Tenant is already active");
    }

    private void checkIfTenantProvisioning(Tenant tenant) {
        if (tenant.getStatus().equals(TenantStatus.PROVISIONING))
            throw new BusinessException("Tenant provisioning already in progress");
    }

    private void checkIfTenantNameAndCodeAlreadyExists(String companyName, String companyCode) {
        Optional<Tenant> tenantByName = tenantRepository.findByCompanyName(companyName);
        Optional<Tenant> tenantByCode = tenantRepository.findByCompanyCode(companyCode);

        if (tenantByName.isPresent())
            throw new BusinessException("Tenant with the same company name already exists");

        if (tenantByCode.isPresent())
            throw new BusinessException("Tenant with the same company code already exists");
    }
}
