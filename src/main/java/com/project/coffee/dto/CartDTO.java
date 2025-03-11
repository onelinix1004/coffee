package com.project.coffee.dto;

import com.project.coffee.entity.Cart;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartDTO {
    private Long id;
    private Long userId;
    private Long storeId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private int totalItems;
    private double totalAmount;
    private Set<Long> productIds;

    public static Cart convertToEntity(CartDTO dto) {
        return Cart.builder()
                .id(dto.getId())
                .totalItems(dto.getTotalItems())
                .totalAmount(dto.getTotalAmount())
                .createdAt(dto.getCreatedAt() != null ? dto.getCreatedAt() : LocalDateTime.now())
                .updatedAt(dto.getUpdatedAt() != null ? dto.getUpdatedAt() : LocalDateTime.now())
                .build();
    }
}
