package com.project.coffee.controller;

import com.project.coffee.dto.CartDTO;
import com.project.coffee.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carts")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping
    public ResponseEntity<CartDTO> createCart(@RequestBody CartDTO cartDTO) {
        return ResponseEntity.ok(cartService.createCart(cartDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartDTO> getCartById(@PathVariable Long id) {
        return ResponseEntity.ok(cartService.getCartById(id));
    }

    @GetMapping
    public ResponseEntity<List<CartDTO>> getAllCarts() {
        return ResponseEntity.ok(cartService.getAllCarts());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CartDTO> updateCart(@PathVariable Long id, @RequestBody CartDTO cartDTO) {
        return ResponseEntity.ok(cartService.updateCart(id, cartDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCart(@PathVariable Long id) {
        cartService.deleteCart(id);
        return ResponseEntity.noContent().build();
    }
}
