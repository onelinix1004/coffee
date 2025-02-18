package com.project.backend.service;


import com.project.backend.dto.CartDTO;
import com.project.backend.entity.Cart;

import java.util.List;

public interface CartService {
    List<CartDTO> getCartByUserId(Long userId);
    CartDTO addToCart(Cart cart);
    CartDTO updateCart(Long id, int quantity);
    void removeCartItem(Long id);
    void clearCart(Long userId);
}

