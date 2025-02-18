package com.project.backend.controller.order;

import com.project.backend.dto.order.OrderItemDTO;
import com.project.backend.service.order.OrderItemService;
import com.project.backend.entity.order.OrderItem;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/order-items")
@RequiredArgsConstructor
public class OrderItemController {

    private final OrderItemService orderItemService;

    /**
     * Get all order items in the database.
     *
     * @return A list of order items.
     */
    @GetMapping
    public List<OrderItemDTO> getAllOrderItems() {
        return orderItemService.getAllOrderItems();
    }

    /**
     * Retrieves an order item by its ID.
     *
     * @param id The ID of the order item to be retrieved.
     * @return A ResponseEntity containing the order item if it exists, or a 404 if not.
     */
    @GetMapping("/{id}")
    public ResponseEntity<OrderItemDTO> getOrderItemById(@PathVariable Long id) {
        Optional<OrderItemDTO> orderItem = orderItemService.getOrderItemById(id);
        return orderItem.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Creates a new order item.
     *
     * @param newOrderItem The information of the order item to be created.
     * @return A ResponseEntity containing the newly created order item.
     */
    @PostMapping
    public ResponseEntity<OrderItemDTO> createOrderItem(@RequestBody final OrderItem newOrderItem) {
        final OrderItemDTO createdOrderItem = orderItemService.createOrderItem(newOrderItem);
        return ResponseEntity.created(null).body(createdOrderItem);
    }



    /**
     * Updates an existing order item.
     *
     * @param id            The ID of the order item to be updated.
     * @param updatedOrderItem The updated order item information.
     * @return The updated order item.
     */
    @PutMapping("/{id}")
    public OrderItemDTO updateOrderItem(@PathVariable Long id, @RequestBody OrderItem updatedOrderItem) {
        return orderItemService.updateOrderItem(id, updatedOrderItem);
    }


    /**
     * Deletes an order item.
     *
     * @param orderId The ID of the order item to be deleted.
     * @return A ResponseEntity with a 204 No Content status.
     */
    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrderItem(@PathVariable Long orderId) {
        orderItemService.deleteOrderItem(orderId);
        return ResponseEntity.noContent().build();
    }
}

