package com.project.coffee.service.impl;

import com.project.coffee.entity.Order;
import com.project.coffee.exception.BadRequestException;
import com.project.coffee.exception.ResourceNotFoundException;
import com.project.coffee.repository.OrderRepository;
import com.project.coffee.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    /**
     * Retrieves a list of all orders from the repository.
     *
     * @return a list of OrderDTO objects representing all orders
     */
    @Override
    public List<OrderDTO> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves an order by its ID from the repository.
     *
     * @param orderId the ID of the order to retrieve
     * @return an Optional containing the OrderDTO object if found, empty otherwise
     */
    @Override
    public Optional<OrderDTO> getOrderById(Integer orderId) {
        return orderRepository.findById(orderId)
                .map(this::convertToDTO);
    }

    /**
     * Creates a new order and stores it in the repository.
     *
     * @param orderDTO the OrderDTO to create an order from
     * @return an OrderDTO representing the newly created order
     * @throws BadRequestException if the order creation fails due to invalid input
     */
    @Override
    public OrderDTO createOrder(OrderDTO orderDTO) {
        if (orderDTO.getUserId() == null || orderDTO.getStoreId() == null) {
            throw new BadRequestException("User ID and Store ID are required for an order");
        }
        if (orderDTO.getTotalAmount() == null || orderDTO.getTotalAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new BadRequestException("Total amount must be greater than zero");
        }
        if (orderDTO.getStatus() == null || !orderDTO.getStatus().matches("pending|processing|shipped|delivered|cancelled")) {
            throw new BadRequestException("Invalid order status");
        }
        Order order = convertToEntity(orderDTO);
        Order savedOrder = orderRepository.save(order);
        return convertToDTO(savedOrder);
    }

    /**
     * Updates an existing order in the repository with the provided details.
     *
     * @param orderId the ID of the order to update
     * @param orderDTO the OrderDTO containing the updated order details
     * @return an OrderDTO representing the updated order
     * @throws ResourceNotFoundException if no order is found with the given ID
     */
    @Override
    public OrderDTO updateOrder(Integer orderId, OrderDTO orderDTO) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with ID: " + orderId));
        order.setTotalAmount(orderDTO.getTotalAmount());
        order.setStatus(orderDTO.getStatus());
        order.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        order.setTrackingCode(orderDTO.getTrackingCode());
        Order updatedOrder = orderRepository.save(order);
        return convertToDTO(updatedOrder);
    }


    /**
     * Deletes an order from the repository by its ID.
     *
     * @param orderId the ID of the order to delete
     * @throws ResourceNotFoundException if no order is found with the given ID
     */
    @Override
    public void deleteOrder(Integer orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with ID: " + orderId));
        orderRepository.delete(order);
    }

    /**
     * Converts an Order object to an OrderDTO object.
     *
     * @param order the Order object to convert
     * @return the OrderDTO object representing the given Order
     */
    private OrderDTO convertToDTO(Order order) {
        OrderDTO dto = new OrderDTO();
        dto.setOrderId(order.getOrderId());
        dto.setTotalAmount(order.getTotalAmount());
        dto.setStatus(order.getStatus());
        dto.setCreatedAt(order.getCreatedAt());
        dto.setUpdatedAt(order.getUpdatedAt());
        dto.setTrackingCode(order.getTrackingCode());
        return dto;
    }

    /**
     * Converts an OrderDTO object to an Order object.
     *
     * @param dto the OrderDTO object to convert
     * @return the Order object representing the given OrderDTO
     */
    private Order convertToEntity(OrderDTO dto) {
        Order order = new Order();
        order.setTotalAmount(dto.getTotalAmount());
        order.setStatus(dto.getStatus());
        order.setTrackingCode(dto.getTrackingCode());
        return order;
    }
}