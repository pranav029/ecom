package com.ecom.product.services;

import com.ecom.product.entities.Variant;
import com.ecom.product.repositories.VariantRepository;
import com.ecom.product.validators.ProductValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VariantService {
    private final VariantRepository variantRepository;

    private final ProductValidator productValidator;

    public List<Variant> findAll() {
        return variantRepository.findAll();
    }

    public Optional<Variant> findById(String id) {
        return variantRepository.findById(id);
    }

    public Variant save(Variant variant) {
        productValidator.validateProduct(variant.getProduct().getId());

        return variantRepository.save(variant);
    }

    public void deleteById(String variantId) {
        productValidator.validateProduct(variantId);

        variantRepository.deleteById(variantId);
    }
}

