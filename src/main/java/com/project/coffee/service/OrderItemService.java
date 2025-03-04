package com.project.coffee.service;


import com.project.coffee.dto.OrderItemDTO;

import java.util.List;

public interface OrderItemService {

    /**
     * Retrieves a list of all order items from the repository.
     *
     * @return a list of OrderItemDTO objects representing all order items
     */
    List<OrderItemDTO> getAllOrderItems();

    /**
     * Retrieves an order item by its ID from the repository.
     *
     * @param orderItemId the ID of the order item to retrieve
     * @return an Optional containing the OrderItemDTO object if found, empty otherwise
     */
    java.util.Optional<OrderItemDTO> getOrderItemById(Integer orderItemId);

    /**
     * Creates a new order item and stores it in the repository.
     *
     * @param orderItemDTO the OrderItemDTO to create an order item from
     * @return an OrderItemDTO representing the newly created order item
     * @throws com.project.coffee.exception.BadRequestException if the order item creation fails due to invalid input
     */
    OrderItemDTO createOrderItem(OrderItemDTO orderItemDTO);

    /**
     * Updates an existing order item in the repository with the provided details.
     *
     * @param orderItemId the ID of the order item to update
     * @param orderItemDTO the OrderItemDTO containing the updated order item details
     * @return an OrderItemDTO representing the updated order item
     * @throws com.project.coffee.exception.ResourceNotFoundException if no order item is found with the given ID
     */
    OrderItemDTO updateOrderItem(Integer orderItemId, OrderItemDTO orderItemDTO);

    /**
     * Deletes an order item from the repository by its ID.
     *
     * @param orderItemId the ID of the order item to delete
     * @throws com.project.coffee.exception.ResourceNotFoundException if no order item is found with the given ID
     */
    void deleteOrderItem(Integer orderItemId);
}