package com.project.backend.service.impl.order;

import com.project.backend.dto.order.OrderItemDTO;
import com.project.backend.repository.order.OrderItemRepository;
import com.project.backend.service.order.OrderItemService;
import com.project.backend.entity.order.OrderItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {

    private final OrderItemRepository orderItemRepository;

    @Override
    public List<OrderItemDTO> getAllOrderItems() {
        return orderItemRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<OrderItemDTO> getOrderItemById(Long id) {
        return orderItemRepository.findById(id).map(this::convertToDTO);
    }

    @Override
    public OrderItemDTO createOrderItem(OrderItem orderItem) {
        OrderItem savedOrderItem = orderItemRepository.save(orderItem);
        return convertToDTO(savedOrderItem);
    }

    @Override
    public OrderItemDTO updateOrderItem(Long id, OrderItem updatedOrderItem) {
        OrderItem orderItem = orderItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order Item not found"));

        orderItem.setOrder(updatedOrderItem.getOrder());
        orderItem.setProduct(updatedOrderItem.getProduct());
        orderItem.setQuantity(updatedOrderItem.getQuantity());
        orderItem.setPrice(updatedOrderItem.getPrice());

        OrderItem savedOrderItem = orderItemRepository.save(orderItem);
        return convertToDTO(savedOrderItem);
    }

    @Override
    public void deleteOrderItem(Long id) {
        orderItemRepository.deleteById(id);
    }

    private OrderItemDTO convertToDTO(OrderItem orderItem) {
        return OrderItemDTO.builder()
                .id(orderItem.getId())
                .orderId(orderItem.getOrder().getId())
                .productId(orderItem.getProduct().getId())
                .quantity(orderItem.getQuantity())
                .price(orderItem.getPrice())
                .build();
    }
}

