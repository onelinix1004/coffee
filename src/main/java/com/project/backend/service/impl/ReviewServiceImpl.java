package com.project.backend.service.impl;

import com.project.backend.dto.ReviewDTO;
import com.project.backend.repository.ReviewRepository;
import com.project.backend.service.ReviewService;
import com.project.backend.entity.Reviews;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    @Override
    public List<ReviewDTO> getAllReviews() {
        return reviewRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ReviewDTO> getReviewById(Long id) {
        return reviewRepository.findById(id).map(this::convertToDTO);
    }

    @Override
    public List<ReviewDTO> getReviewsByProductId(Long productId) {
        return reviewRepository.findByProductId(productId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ReviewDTO createReview(Reviews review) {
        Reviews savedReview = reviewRepository.save(review);
        return convertToDTO(savedReview);
    }

    @Override
    public ReviewDTO updateReview(Long id, Reviews updatedReview) {
        Reviews review = reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found"));

        review.setRating(updatedReview.getRating());
        review.setComment(updatedReview.getComment());

        Reviews savedReview = reviewRepository.save(review);
        return convertToDTO(savedReview);
    }

    @Override
    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }

    private ReviewDTO convertToDTO(Reviews review) {
        return ReviewDTO.builder()
                .id(review.getId())
                .userId(review.getUser().getId())
                .productId(review.getProduct().getId())
                .rating(review.getRating())
                .comment(review.getComment())
                .createdAt(review.getCreatedAt())
                .build();
    }
}

