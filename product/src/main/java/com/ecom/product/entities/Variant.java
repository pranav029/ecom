package com.ecom.product.entities;

import com.ecom.core.entities.AbstractEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "variants")
public class Variant extends AbstractEntity {
    @Column(name = "sku", nullable = false, unique = true)
    private String sku;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @OneToMany(mappedBy = "variant", cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<Attribute> attributes;
}
