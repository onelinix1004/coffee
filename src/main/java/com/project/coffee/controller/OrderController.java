package com.project.coffee.controller;

import com.project.coffee.dto.order.OrderDTO;
import com.project.coffee.exception.ResourceNotFoundException;
import com.project.coffee.service.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * Retrieves a list of all orders from the repository.
     *
     * @return a ResponseEntity containing a list of OrderDTO objects representing all orders
     */
    @GetMapping
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        List<OrderDTO> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    /**
     * Retrieves an order by its ID from the repository.
     *
     * @param orderId the ID of the order to retrieve
     * @return a ResponseEntity containing the OrderDTO with the given ID if found,
     *         or a 404 response if not found
     */
    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable Integer orderId) {
        return orderService.getOrderById(orderId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(404).body(null)); // Hoặc trả về lỗi JSON
    }

    /**
     * Creates a new order and stores it in the repository.
     *
     * @param orderDTO the OrderDTO to create an order from
     * @return a ResponseEntity containing the newly created OrderDTO,
     *         or a 400 response if the request body is invalid
     */
    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO orderDTO) {
        try {
            OrderDTO createdOrder = orderService.createOrder(orderDTO);
            return ResponseEntity.status(201).body(createdOrder);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(null); // Hoặc trả về lỗi JSON
        }
    }

    /**
     * Updates an existing order in the repository with the provided details.
     *
     * @param orderId the ID of the order to update
     * @param orderDTO the OrderDTO containing the updated order details
     * @return a ResponseEntity containing the updated OrderDTO if found,
     *         or a 404 response if not found
     */
    @PutMapping("/{orderId}")
    public ResponseEntity<OrderDTO> updateOrder(@PathVariable Integer orderId, @RequestBody OrderDTO orderDTO) {
        try {
            OrderDTO updatedOrder = orderService.updateOrder(orderId, orderDTO);
            return ResponseEntity.ok(updatedOrder);
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(404).body(null);
        }
    }

    /**
     * Deletes an order from the repository by its ID.
     *
     * @param orderId the ID of the order to delete
     * @return a ResponseEntity with no content if the deletion is successful,
     *         or a 404 response if the order with the given ID does not exist
     */
    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Integer orderId) {
        try {
            orderService.deleteOrder(orderId);
            return ResponseEntity.ok().build();
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(404).build();
        }
    }
}