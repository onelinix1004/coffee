package com.project.coffee.service.impl;

import com.project.coffee.dto.OrderDTO;
import com.project.coffee.entity.Order;
import com.project.coffee.exception.DuplicateResourceException;
import com.project.coffee.exception.InvalidDataException;
import com.project.coffee.exception.ResourceNotFoundException;
import com.project.coffee.repository.OrderRepository;
import com.project.coffee.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public OrderDTO createOrder(OrderDTO orderDTO) {
        if (orderDTO.getId() != null) {
            throw new InvalidDataException("Order ID should be null");
        }

        if (orderRepository.existsById(orderDTO.getId())) {
            throw new DuplicateResourceException("Order already exists");
        }
        Order order = OrderDTO.convertToEntity(orderDTO);
        order.setOrderDate(LocalDateTime.now());

        Order savedOrder = orderRepository.save(order);
        return convertToDTO(savedOrder);
    }

    @Override
    public OrderDTO getOrderById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));
        return convertToDTO(order);
    }

    @Override
    public List<OrderDTO> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public OrderDTO updateOrder(Long id, OrderDTO orderDTO) {
        Order existingOrder = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));

        existingOrder.setStatus(orderDTO.getStatus());
        existingOrder.setTotalAmount(orderDTO.getTotalAmount());
        existingOrder.setPaymentStatus(orderDTO.getPaymentStatus());
        existingOrder.setTrackingNumber(orderDTO.getTrackingNumber());
        existingOrder.setOrderDate(orderDTO.getOrderDate());

        Order updatedOrder = orderRepository.save(existingOrder);
        return convertToDTO(updatedOrder);
    }

    @Override
    public void deleteOrder(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));
        orderRepository.delete(order);
    }

    private OrderDTO convertToDTO(Order order) {
        return OrderDTO.builder()
                .id(order.getId())
                .status(order.getStatus())
                .orderDate(order.getOrderDate())
                .totalAmount(order.getTotalAmount())
                .paymentStatus(order.getPaymentStatus())
                .trackingNumber(order.getTrackingNumber())
                .userId(order.getUser().getId())
                .storeId(order.getStore().getId())
                .shippingAddressId(order.getShippingAddress().getId())
                .build();
    }
}
