package com.project.backend.service;


import com.project.backend.entity.Coupon;

import java.util.Optional;

public interface CouponService {
    Optional<Coupon> getCouponByCode(String code);
}

