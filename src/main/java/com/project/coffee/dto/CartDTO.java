package com.project.coffee.dto;

import lombok.*;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartDTO {

    private Integer cartId;
    private Integer userId;
    private Integer productId;
    private Integer quantity;
    private Timestamp addedAt;

}