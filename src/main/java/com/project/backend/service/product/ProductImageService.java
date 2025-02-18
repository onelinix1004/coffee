package com.project.backend.service.product;

import com.project.backend.dto.product.ProductImageDTO;
import com.project.backend.entity.product.ProductImage;

import java.util.List;
import java.util.Optional;

public interface ProductImageService {

    List<ProductImageDTO> getAllImages();
    Optional<ProductImageDTO> getImageById(Long id);
    List<ProductImageDTO> getImagesByProductId(Long productId);
    ProductImageDTO createImage(ProductImage productImage);
    ProductImageDTO updateImage(Long id, ProductImage productImage);
    void deleteImage(Long id);
}
