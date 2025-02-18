package com.project.backend.service.order;

import com.project.backend.dto.order.OrderItemDTO;
import com.project.backend.entity.order.OrderItem;

import java.util.List;
import java.util.Optional;

public interface OrderItemService {

    List<OrderItemDTO> getAllOrderItems();
    Optional<OrderItemDTO> getOrderItemById(Long id);
    OrderItemDTO createOrderItem(OrderItem orderItem);
    OrderItemDTO updateOrderItem(Long id, OrderItem orderItem);
    void deleteOrderItem(Long id);
}

