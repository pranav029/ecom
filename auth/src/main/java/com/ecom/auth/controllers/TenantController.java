package com.ecom.auth.controllers;

import com.ecom.auth.common.mappers.MapperUtil;
import com.ecom.auth.dto.request.TenantUpdateRequestDto;
import com.ecom.auth.dto.response.TenantResponseDto;
import com.ecom.auth.services.TenantService;
import com.ecom.core.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tenants")
public class TenantController {
    private final TenantService tenantService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<TenantResponseDto>>> getAll() {
        var response = tenantService.findAll().stream()
                .map(MapperUtil::toResponseDto)
                .toList();
        return ApiResponse.successResponseEntity("Tenants retrieved successfully", response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<TenantResponseDto> getById(@PathVariable String id) {
        var response = tenantService.findById(id)
                .map(MapperUtil::toResponseDto).get();

        return ApiResponse.success("Tenant retrieved successfully", response);
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
