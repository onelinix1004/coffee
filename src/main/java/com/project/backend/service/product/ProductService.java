package com.project.backend.service.product;


import com.project.backend.dto.product.ProductDTO;
import com.project.backend.entity.product.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    /**
     * Retrieves all products from the database.
     *
     * @return A list of ProductDTO objects representing all products.
     */
    List<ProductDTO> getAllProducts();

    /**
     * Retrieves a product by their ID.
     *
     * @param id The ID of the product to be retrieved.
     * @return An Optional containing a ProductDTO if the product exists, or an empty Optional if not.
     */
    Optional<ProductDTO> getProductById(Long id);

    /**
     * Retrieves a product by their slug.
     *
     * @param slug The slug of the product to be retrieved.
     * @return An Optional containing a ProductDTO if the product exists, or an empty Optional if not.
     */
    Optional<ProductDTO> getProductBySlug(String slug);

    /**
     * Creates a new product in the database.
     *
     * @param product The product information to be added to the database.
     * @return A ProductDTO object representing the newly created product.
     */
    ProductDTO createProduct(Product product);

    /**
     * Updates an existing product in the database.
     *
     * @param id      The ID of the product to be updated.
     * @param product The product information to be updated in the database.
     * @return A ProductDTO object representing the updated product.
     */
    ProductDTO updateProduct(Long id, Product product);

    /**
     * Deletes a product from the database.
     *
     * @param id The ID of the product to be deleted.
     */
    void deleteProduct(Long id);
}

