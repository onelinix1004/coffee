package com.project.coffee.entity.store;

import com.project.coffee.entity.Inventory;
import com.project.coffee.entity.store.order.OrderItem;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "tbl_product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    Long id;

    String name;
    double price;
    String description;
    int stockQuantity;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    boolean isAvailable;
    String imageUrl;

    @ManyToOne
    @JoinColumn(name = "store_id", nullable = false)
    Store store;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    Category category;

    @ManyToMany(mappedBy = "products")
    Set<Cart> carts;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    List<Review> reviews;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    List<Inventory> inventories;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    List<OrderItem> orderItems;
}