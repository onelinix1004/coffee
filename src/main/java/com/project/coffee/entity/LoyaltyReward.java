package com.project.coffee.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Entity
@Table(name = "Loyalty_Rewards")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoyaltyReward {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "loyalty_rewards_seq")
    @SequenceGenerator(name = "loyalty_rewards_seq", sequenceName = "loyalty_rewards_seq", allocationSize = 1)
    @Column(name = "reward_id")
    private Integer rewardId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "discount_id", referencedColumnName = "discount_id")
    private Discount discount;

    @Column(name = "points_used")
    private Integer pointsUsed;

    @Column(name = "redeemed_at")
    private Timestamp redeemedAt;
}