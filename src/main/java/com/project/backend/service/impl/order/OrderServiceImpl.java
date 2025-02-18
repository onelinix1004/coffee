package com.project.backend.service.impl.order;


import com.project.backend.dto.order.OrderDTO;
import com.project.backend.repository.order.OrderRepository;
import com.project.backend.service.order.OrderService;
import com.project.backend.entity.order.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    /**
     * Retrieves all orders from the database.
     *
     * @return A list of OrderDTO objects, which represent all orders in the database.
     */
    @Override
    public List<OrderDTO> getAllOrders() {
        // Fetch all orders from the repository
        List<Order> orders = orderRepository.findAll();

        // Convert each Order entity to a OrderDTO
        return orders.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves an order by its ID.
     *
     * @param id The ID of the order to be retrieved.
     * @return An Optional containing an OrderDTO if the order exists, or an empty Optional if not.
     */
    @Override
    public Optional<OrderDTO> getOrderById(Long id) {
        // Find the order by ID and convert it to a OrderDTO if present
        return orderRepository.findById(id).map(this::convertToDTO);
    }

    /**
     * Creates a new order in the database.
     *
     * @param order The order information to be added to the database.
     * @return An OrderDTO object representing the newly created order.
     */
    @Override
    public OrderDTO createOrder(Order order) {
        // Save the order to the database
        Order savedOrder = orderRepository.save(order);

        // Convert the saved Order entity to an OrderDTO
        return convertToDTO(savedOrder);
    }

    /**
     * Updates an existing order in the database.
     *
     * @param id           The ID of the order to be updated.
     * @param updatedOrder The order information to be updated in the database.
     * @return An OrderDTO object representing the updated order.
     */
    @Override
    public OrderDTO updateOrder(Long id, Order updatedOrder) {
        // Find the order by ID or throw an exception if not found
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        // Update the order's fields with the new information
        order.setUser(updatedOrder.getUser());
        order.setOrderDate(updatedOrder.getOrderDate());
        order.setShippingAddress(updatedOrder.getShippingAddress());
        order.setTotalPrice(updatedOrder.getTotalPrice());
        order.setOrderStatus(updatedOrder.getOrderStatus());
        order.setShippingMethod(updatedOrder.getShippingMethod());

        // Save the updated order to the database
        Order savedOrder = orderRepository.save(order);

        // Convert the saved Order entity to an OrderDTO
        return convertToDTO(savedOrder);
    }

    /**
     * Deletes an order from the database.
     *
     * @param id The ID of the order to be deleted.
     */
    @Override
    public void deleteOrder(Long id) {
        // Delete the order by ID from the repository
        orderRepository.deleteById(id);
    }

    /**
     * Converts an Order entity to an OrderDTO.
     *
     * @param order The Order entity to be converted.
     * @return An OrderDTO object representing the converted Order.
     */
    private OrderDTO convertToDTO(Order order) {
        // Create a new OrderDTO from the Order's fields
        return OrderDTO.builder()
                .id(order.getId()) // Copy the order's ID
                .userId(order.getUser().getId()) // Copy the order's user ID
                .orderDate(order.getOrderDate()) // Copy the order's order date
                .shippingAddress(order.getShippingAddress()) // Copy the order's shipping address
                .totalPrice(order.getTotalPrice()) // Copy the order's total price
                .orderStatus(order.getOrderStatus()) // Copy the order's order status
                .shippingMethodId(order.getShippingMethod().getId()) // Copy the order's shipping method ID
                .build(); // Build the OrderDTO
    }
}

