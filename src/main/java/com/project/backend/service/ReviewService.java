package com.project.backend.service;

import com.project.backend.dto.ReviewDTO;
import com.project.backend.entity.Reviews;

import java.util.List;
import java.util.Optional;

public interface ReviewService {

    List<ReviewDTO> getAllReviews();
    Optional<ReviewDTO> getReviewById(Long id);
    List<ReviewDTO> getReviewsByProductId(Long productId);
    ReviewDTO createReview(Reviews review);
    ReviewDTO updateReview(Long id, Reviews review);
    void deleteReview(Long id);
}

