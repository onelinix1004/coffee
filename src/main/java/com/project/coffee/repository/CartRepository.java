package com.project.coffee.repository;

import com.project.coffee.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUserIdAndStoreId(Long userId, Long storeId);
    List<Cart> findByStoreId(Long storeId);
}
