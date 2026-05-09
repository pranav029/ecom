package org.ecom.product.controllers;

import lombok.RequiredArgsConstructor;
import org.ecom.product.entities.Variant;
import org.ecom.product.services.VariantService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/variants")
public class VariantController {
    private final VariantService variantService;

    @GetMapping
    public List<Variant> getAll() {
        return variantService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Variant> getById(@PathVariable String id) {
        return variantService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Variant create(@RequestBody Variant variant) {
        return variantService.save(variant);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Variant> update(@PathVariable String id, @RequestBody Variant variant) {
        return variantService.findById(id)
                .map(existing -> {
                    variant.setId(id);
                    return ResponseEntity.ok(variantService.save(variant));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        if (variantService.findById(id).isPresent()) {
            variantService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

