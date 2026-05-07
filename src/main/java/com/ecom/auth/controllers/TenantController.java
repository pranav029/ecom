package com.ecom.auth.controllers;

import com.ecom.auth.dto.TenantRequestDto;
import com.ecom.auth.dto.TenantResponseDto;
import com.ecom.auth.entities.Tenant;
import com.ecom.auth.services.TenantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tenants")
public class TenantController {
    private final TenantService tenantService;

    @GetMapping
    public List<TenantResponseDto> getAll() {
        return tenantService.findAll().stream()
                .map(tenantService::toResponseDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TenantResponseDto> getById(@PathVariable String id) {
        return tenantService.findById(id)
                .map(tenantService::toResponseDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public TenantResponseDto create(@RequestBody TenantRequestDto dto) {
        Tenant tenant = tenantService.fromRequestDto(dto);
        return tenantService.toResponseDto(tenantService.save(tenant));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TenantResponseDto> update(@PathVariable String id, @RequestBody TenantRequestDto dto) {
        return tenantService.findById(id)
                .map(existing -> {
                    Tenant updated = tenantService.fromRequestDto(dto);
                    updated.setId(id);
                    return ResponseEntity.ok(tenantService.toResponseDto(tenantService.save(updated)));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        if (tenantService.findById(id).isPresent()) {
            tenantService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
