package com.project.backend.dto.order;


import com.project.backend.entity.order.OrderStatus;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDTO {

    private Long id;
    private Long userId;
    private LocalDateTime orderDate;
    private String shippingAddress;
    private BigDecimal totalPrice;
    private OrderStatus orderStatus;
    private Long shippingMethodId;
}

