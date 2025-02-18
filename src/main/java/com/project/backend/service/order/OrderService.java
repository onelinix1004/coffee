package com.project.backend.service.order;

import com.project.backend.dto.order.OrderDTO;
import com.project.backend.entity.order.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    /**
     * Retrieves all orders from the database.
     *
     * @return A list of OrderDTO objects, which represent all orders in the database.
     */
    List<OrderDTO> getAllOrders();

    /**
     * Retrieves an order by its ID.
     *
     * @param id The ID of the order to be retrieved.
     * @return An Optional containing an OrderDTO if the order exists, or an empty Optional if not.
     */
    Optional<OrderDTO> getOrderById(Long id);

    /**
     * Creates a new order in the database.
     *
     * @param order The order information to be added to the database.
     * @return An OrderDTO object representing the newly created order.
     */
    OrderDTO createOrder(Order order);

    /**
     * Updates an existing order in the database.
     *
     * @param id    The ID of the order to be updated.
     * @param order The order information to be updated in the database.
     * @return An OrderDTO object representing the updated order.
     */
    OrderDTO updateOrder(Long id, Order order);

    /**
     * Deletes an order from the database.
     *
     * @param id The ID of the order to be deleted.
     */
    void deleteOrder(Long id);
}

