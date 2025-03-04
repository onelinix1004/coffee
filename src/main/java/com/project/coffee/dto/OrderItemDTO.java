package com.project.coffee.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDTO {
    private Long id;
    private int quantity;
    private Long orderId;
    private Long productId;
    private double unitPrice;
    private double subtotal;
    private double discount;
}
