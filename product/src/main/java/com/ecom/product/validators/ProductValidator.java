package com.ecom.product.validators;

import com.ecom.product.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductValidator {
    private final ProductRepository productRepository;

    public void validateProduct(String productId) {
        if (!productRepository.existsById(productId)) {
            throw new IllegalArgumentException("Product with ID " + productId + " does not exist.");
        }
    }
}
