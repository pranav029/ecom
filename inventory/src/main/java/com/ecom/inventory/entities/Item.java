package com.ecom.inventory.entities;

import com.ecom.core.entities.AbstractEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "items")
public class Item extends AbstractEntity {
    @Column(name = "sku", nullable = false)
    private String sku;

    @Column(name = "product_id", nullable = false)
    private String productId;

    @Column(name = "quantity_available", nullable = false)
    private Integer quantityAvailable = 0;

    @Column(name = "quantity_reserved", nullable = false)
    private Integer quantityReserved = 0;

    @Column(name = "quantity_sold", nullable = false)
    private Integer quantitySold = 0;

    @ManyToOne
    @JoinColumn(name = "warehouse_id")
    private Warehouse warehouse;
}
