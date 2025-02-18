package com.project.backend.service.impl;


import com.project.backend.dto.CartDTO;
import com.project.backend.entity.Cart;
import com.project.backend.entity.Coupon;
import com.project.backend.repository.CartRepository;
import com.project.backend.repository.CouponRepository;
import com.project.backend.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CouponRepository couponRepository;

    @Override
    public List<CartDTO> getCartByUserId(Long userId) {
        return cartRepository.findByUserId(userId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CartDTO addToCart(Cart cart) {
        Optional<Cart> existingCartItem = cartRepository.findByUserIdAndProductId(
                cart.getUser().getId(), cart.getProduct().getId()
        );

        if (existingCartItem.isPresent()) {
            Cart existingCart = existingCartItem.get();
            existingCart.setQuantity(existingCart.getQuantity() + cart.getQuantity());
            cart = cartRepository.save(existingCart);
        } else {
            cart.setAddedAt(LocalDateTime.now());
            cart = cartRepository.save(cart);
        }

        return convertToDTO(cart);
    }

    @Override
    public CartDTO updateCart(Long id, int quantity) {
        Cart cart = cartRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cart item not found"));
        cart.setQuantity(quantity);
        Cart updatedCart = cartRepository.save(cart);
        return convertToDTO(updatedCart);
    }

    @Override
    public void removeCartItem(Long id) {
        cartRepository.deleteById(id);
    }

    @Override
    public void clearCart(Long userId) {
        List<Cart> cartItems = cartRepository.findByUserId(userId);
        cartRepository.deleteAll(cartItems);
    }

    public Double applyCoupon(Long userId, String couponCode) {
        Optional<Coupon> optionalCoupon = couponRepository.findByCode(couponCode);

        if (optionalCoupon.isPresent()) {
            Coupon coupon = optionalCoupon.get();

            // Kiểm tra nếu phiếu giảm giá còn hiệu lực
            if (LocalDateTime.now().isBefore(coupon.getStartDate()) || LocalDateTime.now().isAfter(coupon.getEndDate())) {
                throw new RuntimeException("Coupon has expired.");
            }

            // Lấy tổng giá trị đơn hàng
            Double totalAmount = cartRepository.findByUserId(userId).stream()
                    .mapToDouble(cart -> cart.getProduct().getPrice() * cart.getQuantity())
                    .sum();

            // Kiểm tra nếu giá trị đơn hàng đủ điều kiện
            if (totalAmount < coupon.getMinOrder()) {
                throw new RuntimeException("Total order value is less than minimum required for the coupon.");
            }

            // Tính toán giảm giá
            Double discountAmount = coupon.getDiscountType() == Coupon.DiscountType.PERCENT ?
                    totalAmount * (coupon.getDiscount() / 100) :
                    coupon.getDiscount();

            // Kiểm tra mức giảm giá tối đa
            if (coupon.getMaxDiscount() != null && discountAmount > coupon.getMaxDiscount()) {
                discountAmount = coupon.getMaxDiscount();
            }

            return discountAmount;
        }

        return 0.0;
    }

    private CartDTO convertToDTO(Cart cart) {
        return CartDTO.builder()
                .id(cart.getId())
                .userId(cart.getUser().getId())
                .productId(cart.getProduct().getId())
                .quantity(cart.getQuantity())
                .addedAt(cart.getAddedAt())
                .build();
    }
}
