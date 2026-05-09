package org.ecom.product.services;

import org.ecom.product.entities.Attribute;
import org.ecom.product.repositories.AttributeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AttributeService {
    private final AttributeRepository attributeRepository;

    public AttributeService(AttributeRepository attributeRepository) {
        this.attributeRepository = attributeRepository;
    }

    public List<Attribute> findAll() {
        return attributeRepository.findAll();
    }

    public Optional<Attribute> findById(String id) {
        return attributeRepository.findById(id);
    }

    public Attribute save(Attribute attribute) {
        return attributeRepository.save(attribute);
    }

    public void deleteById(String id) {
        attributeRepository.deleteById(id);
    }
}

