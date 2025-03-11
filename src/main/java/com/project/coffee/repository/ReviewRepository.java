package com.project.coffee.repository;

import com.project.coffee.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByProductId(Long productId);
    List<Review> findByUserId(Long userId);
    List<Review> findByStoreId(Long storeId);
    List<Review> findByProductIdAndIsVerified(Long productId, boolean isVerified);
}
