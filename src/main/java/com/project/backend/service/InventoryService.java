package com.project.backend.service;

import com.project.backend.entity.Inventory;

import java.util.List;
import java.util.Optional;

public interface InventoryService {
    /**
     * Retrieves a list of Inventory objects associated with the given product ID.
     *
     * @param productId the ID of the product
     * @return a list of Inventory objects
     */
    List<Inventory> getInventoryByProductId(Long productId);

    /**
     * Retrieves an Inventory object associated with the given product ID and variant ID.
     *
     * @param productId  the ID of the product
     * @param variantId  the ID of the variant
     * @return an Inventory object, or an empty optional if no such inventory exists
     */
    Optional<Inventory> getInventoryByProductIdAndVariantId(Long productId, Long variantId);

    /**
     * Updates an existing Inventory object with the given values, and returns the updated Inventory
     * object.
     *
     * @param inventory the Inventory object to update
     * @return the updated Inventory object
     */
    Inventory updateInventory(Inventory inventory);

    /**
     * Adds a new Inventory object to the repository.
     *
     * @param inventory the Inventory object to add
     */
    void addInventory(Inventory inventory);
}

