package com.project.backend.controller.order;


import com.project.backend.dto.order.ShippingMethodDTO;
import com.project.backend.service.order.ShippingMethodService;
import com.project.backend.entity.order.ShippingMethod;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/shipping-methods")
@RequiredArgsConstructor
public class ShippingMethodController {

    private final ShippingMethodService shippingMethodService;

    /**
     * Handles the HTTP GET request to retrieve all shipping methods.
     *
     * @return A list of ShippingMethodDTO objects representing all shipping methods.
     */
    @GetMapping
    public List<ShippingMethodDTO> getAllShippingMethods() {
        return shippingMethodService.getAllShippingMethods();
    }

    /**
     * Handles the HTTP GET request to retrieve a shipping method by its ID.
     *
     * @param id The ID of the shipping method to retrieve.
     * @return A ResponseEntity containing a ShippingMethodDTO object if the shipping method exists, or a 404 NOT FOUND response if the shipping method does not exist.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ShippingMethodDTO> getShippingMethodById(@PathVariable Long id) {
        Optional<ShippingMethodDTO> shippingMethod = shippingMethodService.getShippingMethodById(id);
        return shippingMethod.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Handles the HTTP POST request to create a new shipping method.
     *
     * @param shippingMethod The ShippingMethod object to be added to the database.
     * @return A ShippingMethodDTO object representing the newly created shipping method.
     */
    @PostMapping
    public ShippingMethodDTO createShippingMethod(@RequestBody ShippingMethod shippingMethod) {
        // Delegate the creation of the new shipping method to the shippingMethodService
        return shippingMethodService.createShippingMethod(shippingMethod);
    }

    /**
     * Handles the HTTP PUT request to update an existing shipping method.
     *
     * @param id            The ID of the shipping method to be updated.
     * @param shippingMethod The ShippingMethod object containing the updated shipping method information.
     * @return A ShippingMethodDTO object representing the updated shipping method.
     */
    @PutMapping("/{id}")
    public ShippingMethodDTO updateShippingMethod(@PathVariable Long id, @RequestBody ShippingMethod shippingMethod) {
        // Delegate the update of the shipping method to the shippingMethodService
        return shippingMethodService.updateShippingMethod(id, shippingMethod);
    }

    /**
     * Handles the HTTP DELETE request to delete a shipping method by its ID.
     *
     * @param id The ID of the shipping method to be deleted.
     * @return A ResponseEntity containing a 204 NO CONTENT response if the shipping method is successfully deleted, or a 404 NOT FOUND response if the shipping method does not exist.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteShippingMethod(@PathVariable Long id) {
        // Delegate the deletion of the shipping method to the shippingMethodService
        shippingMethodService.deleteShippingMethod(id);
        // Return a 204 NO CONTENT response to indicate that the request was successful
        return ResponseEntity.noContent().build();
    }
}

