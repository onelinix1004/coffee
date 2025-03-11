package com.project.coffee.service;

import com.project.coffee.dto.CartDTO;
import java.util.List;

public interface CartService {
    CartDTO createCart(CartDTO cartDTO);
    CartDTO getCartById(Long id);
    List<CartDTO> getAllCarts();
    CartDTO updateCart(Long id, CartDTO cartDTO);
    void deleteCart(Long id);
}
