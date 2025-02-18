package com.project.backend.dto.order;


import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShippingMethodDTO {

    private Long id;
    private String name;
    private BigDecimal cost;
}

