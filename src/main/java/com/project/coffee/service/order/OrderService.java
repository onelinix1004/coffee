package com.project.coffee.service.order;


import com.project.coffee.dto.order.OrderDTO;

import java.util.List;

public interface OrderService {

    /**
     * Retrieves a list of all orders from the repository.
     *
     * @return a list of OrderDTO objects representing all orders
     */
    List<OrderDTO> getAllOrders();

    /**
     * Retrieves an order by its ID from the repository.
     *
     * @param orderId the ID of the order to retrieve
     * @return an OrderDTO object representing the order with the given ID
     * @throws com.project.coffee.exception.ResourceNotFoundException if no order is found with the given ID
     */
    OrderDTO getOrderById(Integer orderId);

    /**
     * Creates a new order and stores it in the repository.
     *
     * @param orderDTO the OrderDTO to create an order from
     * @return an OrderDTO representing the newly created order
     * @throws com.project.coffee.exception.BadRequestException if the order creation fails due to invalid input
     */
    OrderDTO createOrder(OrderDTO orderDTO);

    /**
     * Updates an existing order in the repository with the provided details.
     *
     * @param orderId the ID of the order to update
     * @param orderDTO the OrderDTO containing the updated order details
     * @return an OrderDTO representing the updated order
     * @throws com.project.coffee.exception.ResourceNotFoundException if no order is found with the given ID
     */
    OrderDTO updateOrder(Integer orderId, OrderDTO orderDTO);

    /**
     * Deletes an order from the repository by its ID.
     *
     * @param orderId the ID of the order to delete
     * @throws com.project.coffee.exception.ResourceNotFoundException if no order is found with the given ID
     */
    void deleteOrder(Integer orderId);
}
