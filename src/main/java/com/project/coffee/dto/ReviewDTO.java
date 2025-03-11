package com.project.coffee.dto;

import com.project.coffee.entity.Review;
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

    public static Review convertToEntity(ReviewDTO dto) {
        return Review.builder()
                .id(dto.getId())
                .rating(dto.getRating())
                .comment(dto.getComment())
                .reviewDate(dto.getReviewDate() != null ? dto.getReviewDate() : LocalDateTime.now())
                .isVerified(dto.isVerified())
                .title(dto.getTitle())
                .build();
    }
}
