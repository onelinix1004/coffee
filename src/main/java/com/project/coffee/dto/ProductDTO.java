package com.project.coffee.dto;

import com.project.coffee.entity.store.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private Long id;
    private String name;
    private double price;
    private Long storeId;
    private Long categoryId;
    private String description;
    private int stockQuantity;
    private boolean isAvailable;
    private String imageUrl;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static Product convertToEntity(ProductDTO dto) {
        return Product.builder()
                .id(dto.getId())
                .name(dto.getName())
                .price(dto.getPrice())
                .description(dto.getDescription())
                .stockQuantity(dto.getStockQuantity())
                .isAvailable(dto.isAvailable())
                .imageUrl(dto.getImageUrl())
                .build();
    }
}
