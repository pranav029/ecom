package com.ecom.product.controllers;

import lombok.RequiredArgsConstructor;
import com.ecom.product.entities.Attribute;
import com.ecom.product.services.AttributeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/attributes")
public class AttributeController {
    private final AttributeService attributeService;

    @GetMapping
    public List<Attribute> getAll() {
        return attributeService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Attribute> getById(@PathVariable String id) {
        return attributeService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Attribute create(@RequestBody Attribute attribute) {
        return attributeService.save(attribute);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Attribute> update(@PathVariable String id, @RequestBody Attribute attribute) {
        return attributeService.findById(id)
                .map(existing -> {
                    attribute.setId(id);
                    return ResponseEntity.ok(attributeService.save(attribute));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        if (attributeService.findById(id).isPresent()) {
            attributeService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

