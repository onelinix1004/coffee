package com.project.coffee.controller;


import com.project.coffee.dto.order.OrderDTO;
import com.project.coffee.service.order.OrderService;
import com.project.coffee.exception.ResourceNotFoundException;
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
     * @return a list of OrderDTO objects representing all orders
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
     * @return a ResponseEntity containing the order with the given ID if found,
     *         or a 404 response if not found
     */
    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable Integer orderId) {
        try {
            OrderDTO order = orderService.getOrderById(orderId);
            return ResponseEntity.ok(order);
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(404).body(null);
        }
    }

    /**
     * Creates a new order and stores it in the repository.
     *
     * @param orderDTO the OrderDTO object containing details of the order to be created
     * @return a ResponseEntity containing the newly created OrderDTO, or a 400 response if creation fails
     */
    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO orderDTO) {
        try {
            OrderDTO createdOrder = orderService.createOrder(orderDTO);
            return ResponseEntity.status(201).body(createdOrder);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    /**
     * Updates an existing order with the given ID using the provided details.
     *
     * @param orderId the ID of the order to update
     * @param orderDTO the OrderDTO object containing the updated details of the order
     * @return a ResponseEntity containing the updated OrderDTO,
     *         or a 404 response if the order is not found
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
     * @return a ResponseEntity with no content
     * @throws com.project.coffee.exception.ResourceNotFoundException if no order is found with the given ID
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
