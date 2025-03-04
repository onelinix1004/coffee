package com.project.coffee.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_inventory")
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inventory_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @Column(name = "quantity_in_stock")
    private int quantityInStock;

    @Column(name = "last_updated")
    private LocalDateTime lastUpdated;

    @Column(name = "min_threshold")
    private int minThreshold;

    @Column(name = "max_threshold")
    private int maxThreshold;

    private String status;
}
