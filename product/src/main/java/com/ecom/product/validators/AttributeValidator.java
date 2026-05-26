package com.ecom.product.validators;

import com.ecom.product.repositories.AttributeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AttributeValidator {
    private final AttributeRepository attributeRepository;

    public void validateAttributeWithId(String attributeId) {
        if (!attributeRepository.existsById(attributeId)) {
            throw new IllegalArgumentException("The given attribute does not exist.");
        }
    }
}
