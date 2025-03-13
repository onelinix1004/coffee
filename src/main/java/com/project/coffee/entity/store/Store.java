package com.project.coffee.entity.store;

import com.project.coffee.entity.Inventory;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "tbl_store")
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    Long id;

    String name;
    String location;
    String phoneNumber;
    String email;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    boolean isActive;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    List<Product> products;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    List<Inventory> inventories;

}