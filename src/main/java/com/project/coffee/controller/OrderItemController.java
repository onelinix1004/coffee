package com.project.coffee.controller;

import com.project.coffee.dto.order.OrderItemDTO;
import com.project.coffee.exception.ResourceNotFoundException;
import com.project.coffee.service.order.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order-items")
public class OrderItemController {

    @Autowired
    private OrderItemService orderItemService;

    /**
     * Retrieves a list of all order items from the repository.
     *
     * @return a ResponseEntity containing a list of OrderItemDTO objects representing all order items
     */
    @GetMapping
    public ResponseEntity<List<OrderItemDTO>> getAllOrderItems() {
        List<OrderItemDTO> orderItems = orderItemService.getAllOrderItems();
        return ResponseEntity.ok(orderItems);
    }

    /**
     * Retrieves an order item by its ID from the repository.
     *
     * @param orderItemId the ID of the order item to retrieve
     * @return a ResponseEntity containing the OrderItemDTO with the given ID if found,
     *         or a 404 response if not found
     */
    @GetMapping("/{orderItemId}")
    public ResponseEntity<OrderItemDTO> getOrderItemById(@PathVariable Integer orderItemId) {
        return orderItemService.getOrderItemById(orderItemId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(404).body(null));
    }

    /**
     * Creates a new order item and stores it in the repository.
     *
     * @param orderItemDTO the OrderItemDTO to create an order item from
     * @return a ResponseEntity containing the newly created OrderItemDTO,
     *         or a 400 response if the request body is invalid
     */
    @PostMapping
    public ResponseEntity<OrderItemDTO> createOrderItem(@RequestBody OrderItemDTO orderItemDTO) {
        try {
            OrderItemDTO createdOrderItem = orderItemService.createOrderItem(orderItemDTO);
            return ResponseEntity.status(201).body(createdOrderItem);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    /**
     * Updates an existing order item in the repository with the provided details.
     *
     * @param orderItemId the ID of the order item to update
     * @param orderItemDTO the OrderItemDTO containing the updated order item details
     * @return a ResponseEntity containing the updated OrderItemDTO if found,
     *         or a 404 response if not found
     */
    @PutMapping("/{orderItemId}")
    public ResponseEntity<OrderItemDTO> updateOrderItem(@PathVariable Integer orderItemId, @RequestBody OrderItemDTO orderItemDTO) {
        try {
            OrderItemDTO updatedOrderItem = orderItemService.updateOrderItem(orderItemId, orderItemDTO);
            return ResponseEntity.ok(updatedOrderItem);
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(404).body(null);
        }
    }

    /**
     * Deletes an order item from the repository by its ID.
     *
     * @param orderItemId the ID of the order item to delete
     * @return a ResponseEntity with no content if the deletion is successful,
     *         or a 404 response if the order item with the given ID does not exist
     */
    @DeleteMapping("/{orderItemId}")
    public ResponseEntity<Void> deleteOrderItem(@PathVariable Integer orderItemId) {
        try {
            orderItemService.deleteOrderItem(orderItemId);
            return ResponseEntity.ok().build();
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(404).build();
        }
    }
}