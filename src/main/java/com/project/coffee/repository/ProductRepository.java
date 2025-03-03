package com.project.coffee.repository;


import com.project.coffee.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    /**
     * Retrieves a list of products whose names contain the specified string,
     * ignoring case considerations.
     *
     * @param name the substring to search for within product names.
     * @return a list of products with names containing the specified substring.
     */
    List<Product> findByNameContainingIgnoreCase(String name);
    /**
     * Retrieves a list of products whose storeId matches the specified value.
     *
     * @param storeId the storeId to search for.
     * @return a list of products with matching storeId.
     */
    List<Product> findByStoreId(Integer storeId);
    /**
     * Retrieves a list of products whose isActive flag matches the specified value.
     *
     * @param isActive the value of the isActive flag to search for.
     * @return a list of products with matching isActive flag.
     */
    List<Product> findByIsActive(Integer isActive);

    /**
     * Retrieves a list of products whose category matches the specified value.
     *
     * @param categoryId the categoryId to search for.
     * @return a list of products with matching category.
     */
    List<Product> findByCategory_CategoryId(Integer categoryId);
}
