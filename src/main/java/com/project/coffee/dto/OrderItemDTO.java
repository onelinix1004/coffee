package com.project.coffee.dto;

import com.project.coffee.entity.OrderItem;
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

    public static OrderItem convertToEntity(OrderItemDTO dto) {
        return OrderItem.builder()
                .id(dto.getId())
                .quantity(dto.getQuantity())
                .unitPrice(dto.getUnitPrice())
                .subtotal(dto.getSubtotal())
                .discount(dto.getDiscount())
                .build();
    }
}
