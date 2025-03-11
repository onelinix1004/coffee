package com.project.coffee.dto;

import com.project.coffee.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private Long id;
    private String status;
    private Long userId; // Thay vì đối tượng User
    private Long storeId; // Thay vì đối tượng Store
    private LocalDateTime orderDate;
    private Long shippingAddressId; // Thay vì đối tượng Address
    private double totalAmount;
    private String paymentStatus;
    private String trackingNumber;
    private List<OrderItemDTO> orderItems;

    public static Order convertToEntity(OrderDTO dto) {
        return Order.builder()
                .id(dto.getId())
                .status(dto.getStatus())
                .orderDate(dto.getOrderDate() != null ? dto.getOrderDate() : LocalDateTime.now())
                .totalAmount(dto.getTotalAmount())
                .paymentStatus(dto.getPaymentStatus())
                .trackingNumber(dto.getTrackingNumber())
                .build();
    }
}
