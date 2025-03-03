package com.project.coffee.dto;

import lombok.*;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DiscountDTO {

    private Integer discountId;
    private Integer storeId;
    private String code;
    private String discountType;
    private BigDecimal value;
    private Timestamp startDate;
    private Timestamp endDate;
    private BigDecimal minOrderValue;
    private Integer isActive;

}