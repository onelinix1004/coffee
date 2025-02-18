package com.project.backend.service.impl;

import com.project.backend.entity.Inventory;
import com.project.backend.repository.InventoryRepository;
import com.project.backend.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;

    @Override
    public List<Inventory> getInventoryByProductId(Long productId) {
        return inventoryRepository.findByProductId(productId);
    }

    @Override
    public Optional<Inventory> getInventoryByProductIdAndVariantId(Long productId, Long variantId) {
        return inventoryRepository.findByProductIdAndVariantId(productId, variantId);
    }

    @Override
    public Inventory updateInventory(Inventory inventory) {
        inventory.setUpdatedAt(LocalDateTime.now());
        return inventoryRepository.save(inventory);
    }

    @Override
    public void addInventory(Inventory inventory) {
        inventory.setUpdatedAt(LocalDateTime.now());
        inventoryRepository.save(inventory);
    }
}

