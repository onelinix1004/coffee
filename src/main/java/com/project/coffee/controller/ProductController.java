package com.project.coffee.controller;

import com.project.coffee.dto.ProductDTO;
import com.project.coffee.service.ProductService;
import com.project.coffee.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * Retrieves a list of all products from the repository.
     *
     * @return a ResponseEntity containing a list of ProductDTO objects representing all products
     */
    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        List<ProductDTO> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    /**
     * Retrieves a product by its ID.
     *
     * @param productId the ID of the product to retrieve
     * @return a ResponseEntity containing the ProductDTO with the given ID if found,
     *         or a 404 response if not found
     */
    @GetMapping("/{productId}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Integer productId) {
        return productService.getProductById(productId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(404).body(null));
    }

    /**
     * Creates a new product and stores it in the repository.
     *
     * @param productDTO a ProductDTO object containing the details of the product to be created
     * @return a ResponseEntity containing the newly created ProductDTO, or a 400 response if the request body is invalid
     */
    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO) {
        try {
            ProductDTO createdProduct = productService.createProduct(productDTO);
            return ResponseEntity.status(201).body(createdProduct);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    /**
     * Updates an existing product in the repository with the provided details.
     *
     * @param productId the ID of the product to update
     * @param productDTO the ProductDTO containing the updated product details
     * @return a ResponseEntity containing the updated ProductDTO if found,
     *         or a 404 response if not found
     */
    @PutMapping("/{productId}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Integer productId, @RequestBody ProductDTO productDTO) {
        try {
            ProductDTO updatedProduct = productService.updateProduct(productId, productDTO);
            return ResponseEntity.ok(updatedProduct);
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(404).body(null);
        }
    }

    /**
     * Deletes a product from the repository by its ID.
     *
     * @param productId the ID of the product to delete
     * @return a ResponseEntity with no content if the deletion is successful,
     *         or a 404 response if the product with the given ID does not exist
     */
    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer productId) {
        try {
            productService.deleteProduct(productId);
            return ResponseEntity.ok().build();
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(404).build();
        }
    }
}