package com.ecom.auth.dto.request;

import com.ecom.auth.entities.UserRole;
import lombok.Data;

@Data
public class UserRequestDto {
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private UserRole role;
    private String tenantId;
}

