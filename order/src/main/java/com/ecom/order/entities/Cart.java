package com.ecom.order.entities;

import com.ecom.core.entities.AbstractEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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
@Table(name = "carts")
public class Cart extends AbstractEntity {
    @Column(name = "user_id", nullable = false)
    private String userId;


    @OneToMany(mappedBy = "cart")
    private Set<CartItem> cartItems;
}
