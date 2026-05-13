package com.ecom.product.entities;

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
    @Column(name = "sku", nullable = false)
    private String sku;

    @ManyToMany(mappedBy = "variants", fetch = FetchType.LAZY)
    private Set<Product> products;

    @OneToMany(mappedBy = "variant", cascade = CascadeType.ALL)
    private Set<Attribute> attributes;
}
