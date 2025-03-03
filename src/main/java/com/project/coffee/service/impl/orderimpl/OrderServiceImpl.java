package com.project.coffee.service.impl.orderimpl;


import com.project.coffee.dto.order.OrderDTO;
import com.project.coffee.entity.order.Order;
import com.project.coffee.exception.BadRequestException;
import com.project.coffee.exception.ResourceNotFoundException;
import com.project.coffee.repository.OrderRepository;
import com.project.coffee.service.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
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
     * @return an OrderDTO object representing the order with the given ID
     * @throws com.project.coffee.exception.ResourceNotFoundException if no order is found with the given ID
     */
    @Override
    public OrderDTO getOrderById(Integer orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with ID: " + orderId));
        return convertToDTO(order);
    }

    /**
     * Creates a new order and stores it in the repository.
     *
     * @param orderDTO the OrderDTO to create an order from
     * @return an OrderDTO representing the newly created order
     * @throws BadRequestException if the order creation fails due to invalid input
     * @throws ResourceNotFoundException if the order creation fails due to a missing resource
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
     * @throws com.project.coffee.exception.ResourceNotFoundException if no order is found with the given ID
     */
    @Override
    public OrderDTO updateOrder(Integer orderId, OrderDTO orderDTO) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with ID: " + orderId));
        order.setUserId(orderDTO.getUserId());
        order.setStoreId(orderDTO.getStoreId());
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
     * @throws com.project.coffee.exception.ResourceNotFoundException if no order is found with the given ID
     */
    @Override
    public void deleteOrder(Integer orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with ID: " + orderId));
        orderRepository.delete(order);
    }

    /**
     * Converts an Order entity into an OrderDTO.
     *
     * @param order The Order entity to convert
     * @return The converted OrderDTO object
     */
    private OrderDTO convertToDTO(Order order) {
        OrderDTO dto = new OrderDTO();
        dto.setOrderId(order.getOrderId());
        dto.setUserId(order.getUserId());
        dto.setStoreId(order.getStoreId());
        dto.setTotalAmount(order.getTotalAmount());
        dto.setStatus(order.getStatus());
        dto.setCreatedAt(order.getCreatedAt());
        dto.setUpdatedAt(order.getUpdatedAt());
        dto.setTrackingCode(order.getTrackingCode());
        return dto;
    }

    /**
     * Converts an OrderDTO into an Order entity.
     *
     * @param dto The OrderDTO object to convert
     * @return The converted Order entity
     */
    private Order convertToEntity(OrderDTO dto) {
        Order order = new Order();
        order.setUserId(dto.getUserId());
        order.setStoreId(dto.getStoreId());
        order.setTotalAmount(dto.getTotalAmount());
        order.setStatus(dto.getStatus());
        order.setTrackingCode(dto.getTrackingCode());
        return order;
    }
}
