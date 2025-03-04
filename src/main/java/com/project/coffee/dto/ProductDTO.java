package com.project.coffee.dto;

import com.project.coffee.entity.Category;
import com.project.coffee.entity.Store;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDTO {

    private Integer productId;
    private Store store;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer stockQuantity;
    private Category category;
    private Timestamp createdAt;
    private Integer isActive;

}