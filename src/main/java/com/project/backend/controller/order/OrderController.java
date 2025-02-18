package com.project.backend.controller.order;


import com.project.backend.dto.order.OrderDTO;
import com.project.backend.service.order.OrderService;
import com.project.backend.entity.order.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * Handles the HTTP GET request to retrieve all orders.
     *
     * @return A list of OrderDTO objects representing all orders.
     */
    @GetMapping
    public List<OrderDTO> getAllOrders() {
        return orderService.getAllOrders();
    }

    /**
     * Handles the HTTP GET request to retrieve an order by its ID.
     *
     * @param orderId The ID of the order to be retrieved.
     * @return A ResponseEntity containing an OrderDTO if the order is found, or a 404 status if not.
     */
    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable Long orderId) {
        // Attempt to find the order by ID using the OrderService
        Optional<OrderDTO> optionalOrder = orderService.getOrderById(orderId);

        // Return the order if found, otherwise return a 404 Not Found response
        return optionalOrder.map(ResponseEntity::ok).orElseGet(ResponseEntity.notFound()::build);
    }

    /**
     * Handles the HTTP POST request to create a new order.
     *
     * @param newOrder The new order information to be added to the database.
     * @return A ResponseEntity containing the newly created OrderDTO.
     */
    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(@RequestBody final Order newOrder) {
        // Attempt to create a new order using the OrderService
        OrderDTO newOrderDTO = orderService.createOrder(newOrder);

        // Return a 201 Created response with the newly created OrderDTO
        return ResponseEntity.created(null).body(newOrderDTO);
    }

    /**
     * Handles the HTTP PUT request to update an existing order.
     *
     * @param orderId      The ID of the order to be updated.
     * @param updatedOrder The updated order information.
     * @return An OrderDTO object representing the updated order.
     */
    @PutMapping("/{orderId}")
    public OrderDTO updateOrder(@PathVariable Long orderId, @RequestBody Order updatedOrder) {
        // Delegate the update operation to the OrderService
        return orderService.updateOrder(orderId, updatedOrder);
    }


    /**
     * Handles the HTTP DELETE request to delete an order.
     *
     * When this endpoint is called, it will delete the order with the specified ID.
     *
     * @param orderId The ID of the order to be deleted.
     * @return An empty response with a 204 No Content status.
     */
    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long orderId) {
        // Delegate the delete operation to the OrderService
        orderService.deleteOrder(orderId);

        // Return a 204 No Content response indicating the order was successfully deleted
        return ResponseEntity.noContent().build();
    }
}

