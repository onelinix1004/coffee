package com.project.coffee.service.impl;

import com.project.coffee.dto.ReviewDTO;
import com.project.coffee.entity.Product;
import com.project.coffee.entity.Review;
import com.project.coffee.entity.Store;
import com.project.coffee.entity.User;
import com.project.coffee.exception.ResourceNotFoundException;
import com.project.coffee.repository.ProductRepository;
import com.project.coffee.repository.ReviewRepository;
import com.project.coffee.repository.StoreRepository;
import com.project.coffee.repository.UserRepository;
import com.project.coffee.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;

    @Override
    public ReviewDTO createReview(ReviewDTO reviewDTO) {
        Product product = productRepository.findById(reviewDTO.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        User user = userRepository.findById(reviewDTO.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Store store = storeRepository.findById(reviewDTO.getStoreId())
                .orElseThrow(() -> new ResourceNotFoundException("Store not found"));

        Review review = ReviewDTO.convertToEntity(reviewDTO);
        review.setProduct(product);
        review.setUser(user);
        review.setStore(store);
        review.setReviewDate(LocalDateTime.now());

        Review savedReview = reviewRepository.save(review);
        return convertToDTO(savedReview);
    }

    @Override
    public ReviewDTO getReviewById(Long id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Review not found"));
        return convertToDTO(review);
    }

    @Override
    public List<ReviewDTO> getAllReviews() {
        return reviewRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ReviewDTO updateReview(Long id, ReviewDTO reviewDTO) {
        Review existingReview = reviewRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Review not found"));

        existingReview.setRating(reviewDTO.getRating());
        existingReview.setComment(reviewDTO.getComment());
        existingReview.setVerified(reviewDTO.isVerified());
        existingReview.setTitle(reviewDTO.getTitle());
        existingReview.setReviewDate(LocalDateTime.now());

        Review updatedReview = reviewRepository.save(existingReview);
        return convertToDTO(updatedReview);
    }

    @Override
    public void deleteReview(Long id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Review not found"));
        reviewRepository.delete(review);
    }

    private ReviewDTO convertToDTO(Review review) {
        return ReviewDTO.builder()
                .id(review.getId())
                .rating(review.getRating())
                .comment(review.getComment())
                .reviewDate(review.getReviewDate())
                .isVerified(review.isVerified())
                .title(review.getTitle())
                .productId(review.getProduct().getId())
                .userId(review.getUser().getId())
                .storeId(review.getStore().getId())
                .build();
    }
}
