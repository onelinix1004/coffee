package com.project.coffee.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "Discounts")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Discount {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "discounts_seq")
    @SequenceGenerator(name = "discounts_seq", sequenceName = "discounts_seq", allocationSize = 1)
    @Column(name = "discount_id")
    private Integer discountId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", referencedColumnName = "store_id")
    private Store store;

    @Column(name = "code")
    private String code;

    @Column(name = "discount_type")
    private String discountType;

    @Column(name = "value")
    private BigDecimal value;

    @Column(name = "start_date")
    private Timestamp startDate;

    @Column(name = "end_date")
    private Timestamp endDate;

    @Column(name = "min_order_value")
    private BigDecimal minOrderValue;

    @Column(name = "is_active")
    private Integer isActive;
}