package com.project.coffee.service.impl.orderimpl;


import com.project.coffee.dto.order.OrderItemDTO;
import com.project.coffee.entity.order.OrderItem;
import com.project.coffee.exception.BadRequestException;
import com.project.coffee.exception.ResourceNotFoundException;
import com.project.coffee.repository.OrderItemRepository;
import com.project.coffee.service.order.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;

    /**
     * Retrieves a list of all order items from the repository.
     *
     * @return a list of OrderItemDTO objects representing all order items
     */
    @Override
    public List<OrderItemDTO> getAllOrderItems() {
        List<OrderItem> orderItems = orderItemRepository.findAll();
        return orderItems.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves an order item by its ID from the repository.
     *
     * @param orderItemId the ID of the order item to retrieve
     * @return an OrderItemDTO object representing the order item with the given ID
     * @throws ResourceNotFoundException if no order item is found with the given ID
     */
    @Override
    public OrderItemDTO getOrderItemById(Integer orderItemId) {
        OrderItem orderItem = orderItemRepository.findById(orderItemId)
                .orElseThrow(() -> new ResourceNotFoundException("Order Item not found with ID: " + orderItemId));
        return convertToDTO(orderItem);
    }

    /**
     * Creates a new order item and stores it in the repository.
     *
     * @param orderItemDTO the OrderItemDTO to create an order item from
     * @return an OrderItemDTO representing the newly created order item
     * @throws BadRequestException if the order item creation fails due to invalid input
     */
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

    /**
     * Updates an existing order item in the repository with the given details.
     *
     * @param orderItemId the ID of the order item to update
     * @param orderItemDTO the OrderItemDTO containing the updated order item details
     * @return an OrderItemDTO representing the updated order item
     * @throws ResourceNotFoundException if no order item is found with the given ID
     */
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

    /**
     * Deletes an order item from the repository by its ID.
     *
     * @param orderItemId the ID of the order item to delete
     * @throws ResourceNotFoundException if no order item is found with the given ID
     */
    @Override
    public void deleteOrderItem(Integer orderItemId) {
        OrderItem orderItem = orderItemRepository.findById(orderItemId)
                .orElseThrow(() -> new ResourceNotFoundException("Order Item not found with ID: " + orderItemId));
        orderItemRepository.delete(orderItem);
    }

    /**
     * Converts an OrderItem object to an OrderItemDTO object.
     *
     * @param orderItem the OrderItem object to convert
     * @return the OrderItemDTO object representing the given OrderItem
     */
    private OrderItemDTO convertToDTO(OrderItem orderItem) {
        OrderItemDTO dto = new OrderItemDTO();
        dto.setOrderItemId(orderItem.getOrderItemId());
        dto.setOrderId(orderItem.getOrderId());
        dto.setProductId(orderItem.getProductId());
        dto.setQuantity(orderItem.getQuantity());
        dto.setPriceAtTime(orderItem.getPriceAtTime());
        return dto;
    }

    /**
     * Converts an OrderItemDTO object to an OrderItem object.
     *
     * @param dto the OrderItemDTO object to convert
     * @return the OrderItem object representing the given OrderItemDTO
     */
    private OrderItem convertToEntity(OrderItemDTO dto) {
        OrderItem orderItem = new OrderItem();
        orderItem.setOrderId(dto.getOrderId());
        orderItem.setProductId(dto.getProductId());
        orderItem.setQuantity(dto.getQuantity());
        orderItem.setPriceAtTime(dto.getPriceAtTime());
        return orderItem;
    }
}
