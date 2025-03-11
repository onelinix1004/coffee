package com.project.coffee.repository;

import com.project.coffee.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByStoreId(Long storeId);
    List<Product> findByCategoryId(Long categoryId);
    List<Product> findByIsAvailable(boolean isAvailable);
    List<Product> findByNameContainingIgnoreCase(String name);

    boolean existsByName(String name);
}
