package com.ecom.product.services;

import com.ecom.core.exception.BusinessException;
import com.ecom.product.entities.Attribute;
import com.ecom.product.entities.Variant;
import com.ecom.product.repositories.AttributeRepository;
import com.ecom.product.validators.AttributeValidator;
import com.ecom.product.validators.ProductValidator;
import com.ecom.product.validators.VariantValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AttributeService {
    private final AttributeRepository attributeRepository;

    private final ProductValidator productValidator;
    private final VariantValidator variantValidator;
    private final AttributeValidator attributeValidator;

    public List<Attribute> findAll() {
        return attributeRepository.findAll();
    }

    public Optional<Attribute> findById(String id) {
        return attributeRepository.findById(id);
    }

    public Attribute save(Attribute attribute) {
        variantValidator.validateVariantExists(attribute.getVariant());
        productValidator.validateProduct(attribute.getVariant().getProduct().getId());

        return attributeRepository.save(attribute);
    }

    public void deleteById(String attributeId) {
        attributeValidator.validateAttributeWithId(attributeId);

        attributeRepository.deleteById(attributeId);
    }

}

