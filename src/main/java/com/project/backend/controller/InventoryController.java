package com.project.backend.controller;

import com.project.backend.entity.Inventory;
import com.project.backend.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/inventories")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    /**
     * Retrieves a list of {@link Inventory} objects associated with the given product ID.
     *
     * @param productId the ID of the product
     * @return a list of {@link Inventory} objects
     */
    @GetMapping("/product/{productId}")
    public ResponseEntity<List<Inventory>> getInventoryByProductId(@PathVariable Long productId) {
        List<Inventory> inventories = inventoryService.getInventoryByProductId(productId);
        return ResponseEntity.ok(inventories);
    }

    /**
     * Retrieves an {@link Inventory} object associated with the given product ID and variant ID.
     *
     * @param productId  the ID of the product
     * @param variantId  the ID of the variant
     * @return an {@link Inventory} object, or a 404 response if no such inventory exists
     */
    @GetMapping("/product/{productId}/variant/{variantId}")
    public ResponseEntity<Inventory> getInventoryByProductIdAndVariantId(
            @PathVariable Long productId, @PathVariable Long variantId) {
        Optional<Inventory> inventory = inventoryService.getInventoryByProductIdAndVariantId(productId, variantId);
        return inventory.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping
    public ResponseEntity<Inventory> updateInventory(@RequestBody Inventory inventory) {
        Inventory updatedInventory = inventoryService.updateInventory(inventory);
        return ResponseEntity.ok(updatedInventory);
    }

    @PostMapping
    public ResponseEntity<Inventory> addInventory(@RequestBody Inventory inventory) {
        inventoryService.addInventory(inventory);
        return ResponseEntity.status(201).body(inventory);
    }
}

