package com.project.coffee.repository;

import com.project.coffee.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    Optional<Inventory> findByProductIdAndStoreId(Long productId, Long storeId);
    List<Inventory> findByStoreId(Long storeId);
    List<Inventory> findByProductId(Long productId);
    List<Inventory> findByQuantityInStockLessThan(int threshold);
}
