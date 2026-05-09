package org.ecom.product.services;

import org.ecom.product.entities.Variant;
import org.ecom.product.repositories.VariantRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VariantService {
    private final VariantRepository variantRepository;

    public VariantService(VariantRepository variantRepository) {
        this.variantRepository = variantRepository;
    }

    public List<Variant> findAll() {
        return variantRepository.findAll();
    }

    public Optional<Variant> findById(String id) {
        return variantRepository.findById(id);
    }

    public Variant save(Variant variant) {
        return variantRepository.save(variant);
    }

    public void deleteById(String id) {
        variantRepository.deleteById(id);
    }
}

