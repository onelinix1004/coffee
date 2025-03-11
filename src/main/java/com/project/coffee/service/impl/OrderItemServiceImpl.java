package com.project.coffee.service.impl;

import com.project.coffee.dto.OrderItemDTO;
import com.project.coffee.entity.Order;
import com.project.coffee.entity.OrderItem;
import com.project.coffee.entity.Product;
import com.project.coffee.exception.DuplicateResourceException;
import com.project.coffee.exception.ResourceNotFoundException;
import com.project.coffee.repository.OrderItemRepository;
import com.project.coffee.repository.OrderRepository;
import com.project.coffee.repository.ProductRepository;
import com.project.coffee.service.OrderItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    @Override
    public OrderItemDTO createOrderItem(OrderItemDTO orderItemDTO) {
        Order order = orderRepository.findById(orderItemDTO.getOrderId())
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));
        Product product = productRepository.findById(orderItemDTO.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        if (orderItemRepository.existsById(orderItemDTO.getId())) {
            throw new DuplicateResourceException("Order item already exists");
        }

        OrderItem orderItem = OrderItemDTO.convertToEntity(orderItemDTO);
        orderItem.setOrder(order);
        orderItem.setProduct(product);

        OrderItem savedOrderItem = orderItemRepository.save(orderItem);
        return convertToDTO(savedOrderItem);
    }

    @Override
    public OrderItemDTO getOrderItemById(Long id) {
        OrderItem orderItem = orderItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order item not found"));
        return convertToDTO(orderItem);
    }

    @Override
    public List<OrderItemDTO> getAllOrderItems() {
        return orderItemRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public OrderItemDTO updateOrderItem(Long id, OrderItemDTO orderItemDTO) {
        OrderItem existingOrderItem = orderItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order item not found"));

        existingOrderItem.setQuantity(orderItemDTO.getQuantity());
        existingOrderItem.setUnitPrice(orderItemDTO.getUnitPrice());
        existingOrderItem.setSubtotal(orderItemDTO.getSubtotal());
        existingOrderItem.setDiscount(orderItemDTO.getDiscount());

        OrderItem updatedOrderItem = orderItemRepository.save(existingOrderItem);
        return convertToDTO(updatedOrderItem);
    }

    @Override
    public void deleteOrderItem(Long id) {
        OrderItem orderItem = orderItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order item not found"));
        orderItemRepository.delete(orderItem);
    }

    private OrderItemDTO convertToDTO(OrderItem orderItem) {
        return OrderItemDTO.builder()
                .id(orderItem.getId())
                .quantity(orderItem.getQuantity())
                .unitPrice(orderItem.getUnitPrice())
                .subtotal(orderItem.getSubtotal())
                .discount(orderItem.getDiscount())
                .orderId(orderItem.getOrder().getId())
                .productId(orderItem.getProduct().getId())
                .build();
    }
}
