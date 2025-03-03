package com.project.coffee.service;

import com.project.coffee.dto.ProductDTO;

import java.util.List;

public interface ProductService {

    /**
     * Retrieves a list of all products from the repository.
     *
     * @return a list of ProductDTO objects representing all products
     */
    List<ProductDTO> getAllProducts();

    /**
     * Retrieves a product by its ID from the repository.
     *
     * @param productId the ID of the product to retrieve
     * @return a ProductDTO object representing the product with the given ID
     * @throws com.project.coffee.exception.ResourceNotFoundException if no product is found with the given ID
     */
    ProductDTO getProductById(Integer productId);

    /**
     * Creates a new product and stores it in the repository.
     *
     * @param productDTO the ProductDTO to create a product from
     * @return a ProductDTO representing the newly created product
     * @throws com.project.coffee.exception.BadRequestException if the product creation fails due to invalid input
     */
    ProductDTO createProduct(ProductDTO productDTO);

    /**
     * Updates an existing product in the repository with the provided details.
     *
     * @param productId  the ID of the product to update
     * @param productDTO the ProductDTO containing the updated product details
     * @return a ProductDTO representing the updated product
     * @throws com.project.coffee.exception.ResourceNotFoundException if no product is found with the given ID
     */
    ProductDTO updateProduct(Integer productId, ProductDTO productDTO);

    /**
     * Deletes a product from the repository by its ID.
     *
     * @param productId the ID of the product to delete
     * @throws com.project.coffee.exception.ResourceNotFoundException if no product is found with the given ID
     */
    void deleteProduct(Integer productId);
}