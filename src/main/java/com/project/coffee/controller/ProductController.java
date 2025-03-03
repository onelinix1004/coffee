package com.project.coffee.controller;

import com.project.coffee.dto.ProductDTO;
import com.project.coffee.exception.BadRequestException;
import com.project.coffee.exception.ResourceNotFoundException;
import com.project.coffee.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductServiceImpl productServiceImpl;

    /**
     * Retrieves a list of all products from the database.
     *
     * @return a ResponseEntity containing a list of ProductDTO objects
     *         representing all products
     */
    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        List<ProductDTO> products = productServiceImpl.getAllProducts();
        return ResponseEntity.ok(products);
    }

    /**
     * Retrieves a product by its ID from the database.
     *
     * @param productId the ID of the product to retrieve
     * @return a ResponseEntity containing the product with the given ID if found,
     *         or a 404 response if not found
     */
    @GetMapping("/{productId}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Integer productId) {
        try {
            ProductDTO product = productServiceImpl.getProductById(productId);
            return ResponseEntity.ok(product);
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(404).body(null);
        }
    }

    /**
     * Creates a new product and stores it in the database.
     *
     * @param productDTO the ProductDTO to create a product from
     * @return a ResponseEntity containing the newly created product
     * @throws BadRequestException if the product creation fails
     */
    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO) {
        try {
            ProductDTO createdProduct = productServiceImpl.createProduct(productDTO);
            return ResponseEntity.status(201).body(createdProduct);
        } catch (BadRequestException ex) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    /**
     * Updates an existing product in the database with the provided details.
     *
     * @param productId the ID of the product to update
     * @param productDTO the updated product details
     * @return a ResponseEntity containing the updated ProductDTO
     * @throws ResourceNotFoundException if the product with the given ID is not found
     * @throws BadRequestException if the provided product details are invalid
     */
    @PutMapping("/{productId}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Integer productId, @RequestBody ProductDTO productDTO) {
        try {
            ProductDTO updatedProduct = productServiceImpl.updateProduct(productId, productDTO);
            return ResponseEntity.ok(updatedProduct);
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(404).body(null);
        } catch (BadRequestException ex) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    /**
     * Deletes a product from the database by its ID.
     *
     * @param productId the ID of the product to delete
     * @return a ResponseEntity with no content
     * @throws RuntimeException if the product with the given ID is not found
     */
    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer productId) {
        try {
            productServiceImpl.deleteProduct(productId);
            return ResponseEntity.ok().build();
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(404).build();
        }
    }
}