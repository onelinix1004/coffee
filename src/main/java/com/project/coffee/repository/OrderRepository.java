package com.project.coffee.repository;


import com.project.coffee.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    /**
     * Retrieves a list of orders whose userId matches the specified value.
     *
     * @param userId the userId to search for.
     * @return a list of orders with matching userId.
     */
    List<Order> findByUserId(Integer userId);

    /**
     * Retrieves a list of orders whose status matches the specified value.
     *
     * @param status the status to search for.
     * @return a list of orders with matching status.
     */
    List<Order> findByStatus(String status);

    /**
     * Retrieves a list of orders whose storeId matches the specified value.
     *
     * @param storeId the storeId to search for.
     * @return a list of orders with matching storeId.
     */
    List<Order> findByStoreId(Integer storeId);
}
