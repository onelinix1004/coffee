package com.project.coffee.dto;

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
    private List<OrderItemDTO> orderItems; // Danh sách các mục đơn hàng
}
