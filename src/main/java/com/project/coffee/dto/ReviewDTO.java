package com.project.coffee.dto;

import lombok.*;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewDTO {

    private Integer reviewId;
    private Integer userId;
    private Integer storeId;
    private Integer orderId;
    private Integer rating;
    private String comment;
    private Timestamp createdAt;

}