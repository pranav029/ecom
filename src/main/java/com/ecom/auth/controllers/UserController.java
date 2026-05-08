package com.ecom.auth.controllers;

import com.ecom.auth.dto.request.UserRequestDto;
import com.ecom.auth.dto.response.UserResponseDto;
import com.ecom.auth.entities.User;
import com.ecom.auth.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @GetMapping
    public List<UserResponseDto> getAll() {
        return userService.findAll().stream()
                .map(userService::toResponseDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getById(@PathVariable String id) {
        return userService.findById(id)
                .map(userService::toResponseDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public UserResponseDto create(@RequestBody UserRequestDto dto) {
        User user = userService.fromRequestDto(dto);
        return userService.toResponseDto(userService.save(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> update(@PathVariable String id, @RequestBody UserRequestDto dto) {
        return userService.findById(id)
                .map(existing -> {
                    User updated = userService.fromRequestDto(dto);
                    updated.setId(id);
                    return ResponseEntity.ok(userService.toResponseDto(userService.save(updated)));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        if (userService.findById(id).isPresent()) {
            userService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
