package com.project.coffee.service.impl;


import com.project.coffee.entity.OrderItem;
import com.project.coffee.exception.BadRequestException;
import com.project.coffee.exception.ResourceNotFoundException;
import com.project.coffee.repository.OrderItemRepository;
import com.project.coffee.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Override
    public List<OrderItemDTO> getAllOrderItems() {
        List<OrderItem> orderItems = orderItemRepository.findAll();
        return orderItems.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public OrderItemDTO getOrderItemById(Integer orderItemId) {
        OrderItem orderItem = orderItemRepository.findById(orderItemId)
                .orElseThrow(() -> new ResourceNotFoundException("Order Item not found with ID: " + orderItemId));
        return convertToDTO(orderItem);
    }

    @Override
    public OrderItemDTO createOrderItem(OrderItemDTO orderItemDTO) {
        if (orderItemDTO.getOrderId() == null || orderItemDTO.getProductId() == null) {
            throw new BadRequestException("Order ID and Product ID are required for an order item");
        }
        if (orderItemDTO.getQuantity() == null || orderItemDTO.getQuantity() <= 0) {
            throw new BadRequestException("Quantity must be greater than zero");
        }
        if (orderItemDTO.getPriceAtTime() == null || orderItemDTO.getPriceAtTime().compareTo(BigDecimal.ZERO) <= 0) {
            throw new BadRequestException("Price must be greater than zero");
        }
        OrderItem orderItem = convertToEntity(orderItemDTO);
        OrderItem savedOrderItem = orderItemRepository.save(orderItem);
        return convertToDTO(savedOrderItem);
    }

    @Override
    public OrderItemDTO updateOrderItem(Integer orderItemId, OrderItemDTO orderItemDTO) {
        OrderItem orderItem = orderItemRepository.findById(orderItemId)
                .orElseThrow(() -> new ResourceNotFoundException("Order Item not found with ID: " + orderItemId));
        orderItem.setOrderId(orderItemDTO.getOrderId());
        orderItem.setProductId(orderItemDTO.getProductId());
        orderItem.setQuantity(orderItemDTO.getQuantity());
        orderItem.setPriceAtTime(orderItemDTO.getPriceAtTime());
        OrderItem updatedOrderItem = orderItemRepository.save(orderItem);
        return convertToDTO(updatedOrderItem);
    }

    @Override
    public void deleteOrderItem(Integer orderItemId) {
        OrderItem orderItem = orderItemRepository.findById(orderItemId)
                .orElseThrow(() -> new ResourceNotFoundException("Order Item not found with ID: " + orderItemId));
        orderItemRepository.delete(orderItem);
    }

    private OrderItemDTO convertToDTO(OrderItem orderItem) {
        OrderItemDTO dto = new OrderItemDTO();
        dto.setOrderItemId(orderItem.getOrderItemId());
        dto.setOrderId(orderItem.getOrderId());
        dto.setProductId(orderItem.getProductId());
        dto.setQuantity(orderItem.getQuantity());
        dto.setPriceAtTime(orderItem.getPriceAtTime());
        return dto;
    }

    private OrderItem convertToEntity(OrderItemDTO dto) {
        OrderItem orderItem = new OrderItem();
        orderItem.setOrderId(dto.getOrderId());
        orderItem.setProductId(dto.getProductId());
        orderItem.setQuantity(dto.getQuantity());
        orderItem.setPriceAtTime(dto.getPriceAtTime());
        return orderItem;
    }
}
