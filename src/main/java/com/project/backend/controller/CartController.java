package com.project.backend.controller;

import com.project.backend.dto.CartDTO;
import com.project.backend.entity.Cart;
import com.project.backend.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    /**
     * Retrieve the cart items for a specific user.
     *
     * @param userId the ID of the user whose cart items are to be retrieved
     * @return a list of CartDTO representing the user's cart items
     */
    @GetMapping("/user/{userId}")
    public List<CartDTO> getCartByUserId(@PathVariable Long userId) {
        return cartService.getCartByUserId(userId);
    }

    /**
     * Add a new item to the cart of a user.
     *
     * @param cart the Cart object containing the user, product, and quantity
     * @return a CartDTO representing the added item
     */
    @PostMapping
    public CartDTO addToCart(@RequestBody Cart cart) {
        return cartService.addToCart(cart);
    }

    /**
     * Updates the quantity of an item in the user's cart.
     *
     * @param id       The ID of the cart item to be updated.
     * @param quantity The new quantity for the cart item.
     * @return A CartDTO representing the updated cart item.
     */
    @PutMapping("/{id}")
    public CartDTO updateCart(@PathVariable Long id, @RequestParam int quantity) {
        // Delegate the update operation to the CartService
        return cartService.updateCart(id, quantity);
    }

    /**
     * Deletes a cart item identified by the given ID.
     *
     * @param id The ID of the cart item to be deleted.
     * @return A ResponseEntity indicating the success of the operation.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeCartItem(@PathVariable Long id) {
        // Delegate the deletion operation to the CartService
        cartService.removeCartItem(id);

        // Return a ResponseEntity indicating the success of the operation
        return ResponseEntity.noContent().build();
    }

    /**
     * Clears the cart of a user identified by the given ID.
     *
     * This method will delete all items in the cart of the given user.
     *
     * @param userId The ID of the user whose cart is to be cleared.
     * @return An empty ResponseEntity indicating the success of the operation.
     */
    @DeleteMapping("/user/{userId}")
    public ResponseEntity<Void> clearCart(@PathVariable Long userId) {
        // Delegate the clearing operation to the CartService
        cartService.clearCart(userId);

        // Return a ResponseEntity indicating the success of the operation
        return ResponseEntity.noContent().build();
    }
}

