package com.project.coffee.dto;

import lombok.*;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WishlistDTO {

    private Integer wishlistId;
    private Integer userId;
    private Integer productId;
    private Timestamp addedAt;

}