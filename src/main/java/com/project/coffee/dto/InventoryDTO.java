package com.project.coffee.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InventoryDTO {
    private Long id;
    private Long productId;
    private Long storeId;
    private int quantityInStock;
    private LocalDateTime lastUpdated;
    private int minThreshold;
    private int maxThreshold;
    private String status;
}
