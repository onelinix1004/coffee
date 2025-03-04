package com.project.coffee.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDTO {
    private Long id;
    private int rating;
    private String comment;
    private Long productId;
    private Long userId;
    private Long storeId;
    private LocalDateTime reviewDate;
    private boolean isVerified;
    private String title;
}
