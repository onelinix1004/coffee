package com.project.coffee.repository;

import com.project.coffee.entity.store.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByStoreId(Long storeId);
    List<Product> findByCategoryId(Long categoryId);
    List<Product> findByIsAvailable(boolean isAvailable);
    List<Product> findByNameContainingIgnoreCase(String name);

    boolean existsByName(String name);
    Page<Product> findAll(Pageable pageable);
}
