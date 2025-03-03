package com.project.coffee.dto;

import lombok.*;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoyaltyRewardDTO {

    private Integer rewardId;
    private Integer userId;
    private Integer discountId;
    private Integer pointsUsed;
    private Timestamp redeemedAt;

}