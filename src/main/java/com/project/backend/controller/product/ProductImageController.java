package com.project.backend.controller.product;

import com.project.backend.dto.product.ProductImageDTO;
import com.project.backend.service.product.ProductImageService;
import com.project.backend.entity.product.ProductImage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/product-images")
@RequiredArgsConstructor
public class ProductImageController {

    private final ProductImageService productImageService;

    /**
     * Handles the HTTP GET request to retrieve all product images.
     *
     * @return A list of ProductImageDTO objects representing all product images.
     */
    @GetMapping
    public List<ProductImageDTO> getAllImages() {
        return productImageService.getAllImages();
    }

    /**
     * Handles the HTTP GET request to retrieve a product image by its ID.
     *
     * @param id The ID of the product image to be retrieved.
     * @return A ResponseEntity containing the ProductImageDTO if found, or a 404 status if not found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProductImageDTO> getImageById(@PathVariable Long id) {
        // Retrieve the product image by its ID from the service
        Optional<ProductImageDTO> image = productImageService.getImageById(id);

        // Return the image if found, otherwise return a 404 not found response
        return image.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Handles the HTTP GET request to retrieve all product images by product ID.
     *
     * @param productId The ID of the product for which images are being retrieved.
     * @return A list of ProductImageDTO objects representing the images of the specified product.
     */
    @GetMapping("/product/{productId}")
    public List<ProductImageDTO> getImagesByProductId(@PathVariable Long productId) {
        // Retrieve product images by product ID using the productImageService
        return productImageService.getImagesByProductId(productId);
    }

    /**
     * Handles the HTTP POST request to create a new product image.
     *
     * @param productImage The ProductImage object to be created.
     * @return A ProductImageDTO object representing the newly created product image.
     */
    @PostMapping
    public ProductImageDTO createImage(@RequestBody ProductImage productImage) {
        // Delegate the call to the productImageService to create the product image
        return productImageService.createImage(productImage);
    }

    /**
     * Handles the HTTP PUT request to update an existing product image.
     *
     * @param id            The ID of the product image to be updated.
     * @param productImage The updated product image information.
     * @return The updated product image.
     */
    @PutMapping("/{id}")
    public ProductImageDTO updateImage(@PathVariable Long id, @RequestBody ProductImage productImage) {
        // Delegate the update of the product image to the productImageService
        return productImageService.updateImage(id, productImage);
    }

    /**
     * Handles the HTTP DELETE request to delete a product image.
     *
     * @param id The ID of the product image to be deleted.
     * @return A ResponseEntity containing a 204 status if the deletion is successful, or a 404 status if the product image is not found.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteImage(@PathVariable Long id) {
        // Delete the product image by its ID using the productImageService
        productImageService.deleteImage(id);

        // Return a 204 response if the deletion is successful, otherwise return a 404 not found response
        return ResponseEntity.noContent().build();
    }
}

