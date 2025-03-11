package com.project.coffee.service.impl;

import com.project.coffee.dto.CartDTO;
import com.project.coffee.entity.Cart;
import com.project.coffee.entity.Product;
import com.project.coffee.entity.Store;
import com.project.coffee.entity.User;
import com.project.coffee.exception.ResourceNotFoundException;
import com.project.coffee.repository.CartRepository;
import com.project.coffee.repository.ProductRepository;
import com.project.coffee.repository.StoreRepository;
import com.project.coffee.repository.UserRepository;
import com.project.coffee.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;
    private final ProductRepository productRepository;

    @Override
    public CartDTO createCart(CartDTO cartDTO) {
        User user = userRepository.findById(cartDTO.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Store store = storeRepository.findById(cartDTO.getStoreId())
                .orElseThrow(() -> new ResourceNotFoundException("Store not found"));

        Set<Product> products = new HashSet<>();
        if (cartDTO.getProductIds() != null) {
            products = productRepository.findAllById(cartDTO.getProductIds()).stream().collect(Collectors.toSet());
        }

        Cart cart = CartDTO.convertToEntity(cartDTO);
        cart.setUser(user);
        cart.setStore(store);
        cart.setProducts(products);
        cart.setCreatedAt(LocalDateTime.now());
        cart.setUpdatedAt(LocalDateTime.now());

        Cart savedCart = cartRepository.save(cart);
        return convertToDTO(savedCart);
    }

    @Override
    public CartDTO getCartById(Long id) {
        Cart cart = cartRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found"));
        return convertToDTO(cart);
    }

    @Override
    public List<CartDTO> getAllCarts() {
        return cartRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CartDTO updateCart(Long id, CartDTO cartDTO) {
        Cart existingCart = cartRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found"));

        existingCart.setTotalItems(cartDTO.getTotalItems());
        existingCart.setTotalAmount(cartDTO.getTotalAmount());
        existingCart.setUpdatedAt(LocalDateTime.now());

        Cart updatedCart = cartRepository.save(existingCart);
        return convertToDTO(updatedCart);
    }

    @Override
    public void deleteCart(Long id) {
        Cart cart = cartRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found"));
        cartRepository.delete(cart);
    }

    private CartDTO convertToDTO(Cart cart) {
        return CartDTO.builder()
                .id(cart.getId())
                .userId(cart.getUser().getId())
                .storeId(cart.getStore().getId())
                .totalItems(cart.getTotalItems())
                .totalAmount(cart.getTotalAmount())
                .createdAt(cart.getCreatedAt())
                .updatedAt(cart.getUpdatedAt())
                .productIds(cart.getProducts().stream().map(Product::getId).collect(Collectors.toSet()))
                .build();
    }
}
