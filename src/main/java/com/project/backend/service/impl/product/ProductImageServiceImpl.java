package com.project.backend.service.impl.product;

import com.project.backend.dto.product.ProductImageDTO;
import com.project.backend.repository.product.ProductImageRepository;
import com.project.backend.service.product.ProductImageService;
import com.project.backend.entity.product.ProductImage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductImageServiceImpl implements ProductImageService {

    private final ProductImageRepository productImageRepository;

    @Override
    public List<ProductImageDTO> getAllImages() {
        return productImageRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ProductImageDTO> getImageById(Long id) {
        return productImageRepository.findById(id).map(this::convertToDTO);
    }

    @Override
    public List<ProductImageDTO> getImagesByProductId(Long productId) {
        return productImageRepository.findByProductId(productId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProductImageDTO createImage(ProductImage productImage) {
        ProductImage savedImage = productImageRepository.save(productImage);
        return convertToDTO(savedImage);
    }

    @Override
    public ProductImageDTO updateImage(Long id, ProductImage updatedImage) {
        ProductImage image = productImageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Image not found"));

        image.setImageUrl(updatedImage.getImageUrl());

        ProductImage savedImage = productImageRepository.save(image);
        return convertToDTO(savedImage);
    }

    @Override
    public void deleteImage(Long id) {
        productImageRepository.deleteById(id);
    }

    private ProductImageDTO convertToDTO(ProductImage image) {
        return ProductImageDTO.builder()
                .id(image.getId())
                .productId(image.getProduct().getId())
                .imageUrl(image.getImageUrl())
                .build();
    }
}

