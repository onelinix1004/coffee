package com.project.coffee.dto;

import lombok.*;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDTO {

    private Integer orderId;
    private Integer userId;
    private Integer storeId;
    private BigDecimal totalAmount;
    private String status;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private String trackingCode;
}