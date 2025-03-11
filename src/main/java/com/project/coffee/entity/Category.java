package com.project.coffee.entity;

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
@Table(name = "tbl_category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    Long id;

    String name;
    String description;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    boolean isActive;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    List<Product> products;
}