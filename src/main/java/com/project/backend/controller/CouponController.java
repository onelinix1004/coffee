package com.project.backend.controller;

import com.project.backend.entity.Coupon;
import com.project.backend.service.CouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/coupons")
@RequiredArgsConstructor
public class CouponController {

    private final CouponService couponService;

    /**
     * Retrieve a coupon by its code.
     * @param code The coupon code.
     * @return The coupon if it exists, or a 404 if not.
     */
    @GetMapping("/{code}")
    public ResponseEntity<Coupon> getCoupon(@PathVariable String code) {
        Optional<Coupon> coupon = couponService.getCouponByCode(code);
        return coupon.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}

