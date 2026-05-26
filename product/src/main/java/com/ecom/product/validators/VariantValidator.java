package com.ecom.product.validators;

import com.ecom.product.entities.Attribute;
import com.ecom.product.entities.Variant;
import com.ecom.product.repositories.VariantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VariantValidator {
    private final VariantRepository variantRepository;

    public void validateVariantExists(String variantId) {
        if (!variantRepository.existsById(variantId)) {
            throw new IllegalArgumentException("Variant with ID " + variantId + " does not exist.");
        }
    }

    public void validateVariantExists(Variant variant) {
        if (variant == null) {
            throw new IllegalArgumentException("Variant for the given attribute does not exist.");
        }

        validateVariantExists(variant.getId());
    }
}
