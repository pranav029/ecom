package com.ecom.auth.controllers;

import com.ecom.auth.common.mappers.MapperUtil;
import com.ecom.auth.dto.request.TenantUpdateRequestDto;
import com.ecom.auth.dto.response.TenantResponseDto;
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
                .map(MapperUtil::toResponseDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TenantResponseDto> getById(@PathVariable String id) {
        return tenantService.findById(id)
                .map(MapperUtil::toResponseDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/approve/{tenantId}")
    public ResponseEntity<Void> approveTenant(@PathVariable("tenantId") String tenantId) {
        tenantService.approveTenant(tenantId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<TenantResponseDto> update(@PathVariable String id, @RequestBody TenantUpdateRequestDto dto) {
        return ResponseEntity.ok(tenantService.update(id, dto));
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
