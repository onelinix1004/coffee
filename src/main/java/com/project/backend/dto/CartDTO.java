package com.project.backend.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartDTO {
    private Long id;
    private Long userId;
    private Long productId;
    private Long variantId;
    private int quantity;
    private LocalDateTime addedAt;
}

