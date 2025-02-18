package com.project.backend.controller.product;


import com.project.backend.dto.product.ProductDTO;
import com.project.backend.service.product.ProductService;
import com.project.backend.entity.product.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * Retrieves all products from the database.
     *
     * @return a list of ProductDTO objects representing all products.
     */
    @GetMapping
    public List<ProductDTO> getAllProducts() {
        return productService.getAllProducts();
    }

    /**
     * Retrieves a product by its ID.
     *
     * @param id The ID of the product to be retrieved.
     * @return An Optional containing a ProductDTO if the product exists, or an empty Optional if not.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {
        Optional<ProductDTO> product = productService.getProductById(id);
        return product.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Retrieves a product by its slug.
     *
     * @param productSlug The slug of the product to be retrieved.
     * @return An Optional containing a ProductDTO if the product exists, or an empty Optional if not.
     */
    @GetMapping("/slug/{slug}")
    public ResponseEntity<ProductDTO> getProductBySlug(@PathVariable("slug") String productSlug) {
        // Retrieve a product by its slug
        return productService.getProductBySlug(productSlug)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    /**
     * Creates a new product.
     *
     * @param newProduct The product information to be added to the database.
     * @return A ProductDTO object representing the newly created product.
     */
    @PostMapping
    public ProductDTO createProduct(@RequestBody Product newProduct) {
        // Delegate the creation of the new product to the productService
        return productService.createProduct(newProduct);
    }



    /**
     * Updates an existing product.
     *
     * @param productId The ID of the product to be updated.
     * @param productUpdate The product information to be updated in the database.
     * @return A ProductDTO object representing the updated product.
     */
    @PutMapping("/{id}")
    public ProductDTO updateProduct(@PathVariable Long productId, @RequestBody Product productUpdate) {
        // Delegate the update of the product to the productService
        return productService.updateProduct(productId, productUpdate);
    }

    /**
     * Deletes a product by its ID.
     *
     * @param id The ID of the product to be deleted.
     * @return A ResponseEntity with no content if the deletion is successful.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        // Delegate the deletion of the product to the productService
        productService.deleteProduct(id);
        // Return a response indicating that the product has been successfully deleted
        return ResponseEntity.noContent().build();
    }
}

