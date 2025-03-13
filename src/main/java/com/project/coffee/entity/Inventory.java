package com.project.coffee.entity;

import com.project.coffee.entity.store.Product;
import com.project.coffee.entity.store.Store;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "tbl_inventory")
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inventory_id")
    Long id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    Product product;

    @ManyToOne
    @JoinColumn(name = "store_id", nullable = false)
    Store store;

    @Column(name = "quantity_in_stock")
    int quantityInStock;

    @Column(name = "last_updated")
    LocalDateTime lastUpdated;

    @Column(name = "min_threshold")
    int minThreshold;

    @Column(name = "max_threshold")
    int maxThreshold;

    String status;
}
