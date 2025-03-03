package com.project.coffee.controller;


import com.project.coffee.dto.OrderItemDTO;
import com.project.coffee.service.OrderItemService;
import com.project.coffee.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order-items")
public class OrderItemController {

    @Autowired
    private OrderItemService orderItemService;

    @GetMapping
    public ResponseEntity<List<OrderItemDTO>> getAllOrderItems() {
        List<OrderItemDTO> orderItems = orderItemService.getAllOrderItems();
        return ResponseEntity.ok(orderItems);
    }

    @GetMapping("/{orderItemId}")
    public ResponseEntity<OrderItemDTO> getOrderItemById(@PathVariable Integer orderItemId) {
        try {
            OrderItemDTO orderItem = orderItemService.getOrderItemById(orderItemId);
            return ResponseEntity.ok(orderItem);
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(404).body(null); // Hoặc trả về lỗi JSON
        }
    }

    @PostMapping
    public ResponseEntity<OrderItemDTO> createOrderItem(@RequestBody OrderItemDTO orderItemDTO) {
        try {
            OrderItemDTO createdOrderItem = orderItemService.createOrderItem(orderItemDTO);
            return ResponseEntity.status(201).body(createdOrderItem);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(null); // Hoặc trả về lỗi JSON
        }
    }

    @PutMapping("/{orderItemId}")
    public ResponseEntity<OrderItemDTO> updateOrderItem(@PathVariable Integer orderItemId, @RequestBody OrderItemDTO orderItemDTO) {
        try {
            OrderItemDTO updatedOrderItem = orderItemService.updateOrderItem(orderItemId, orderItemDTO);
            return ResponseEntity.ok(updatedOrderItem);
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(404).body(null);
        }
    }

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
