package com.ecom.auth.dto;

import com.ecom.auth.entities.UserRole;
import lombok.Data;

@Data
public class UserResponseDto {
    private String id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private UserRole role;
    private String tenantId;
}

