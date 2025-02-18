package com.project.backend.controller.user;

import com.project.backend.dto.user.UserAddressDTO;
import com.project.backend.entity.user.UserAddress;
import com.project.backend.service.user.UserAddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user-addresses")
@RequiredArgsConstructor
public class UserAddressController {

    private final UserAddressService userAddressService;

    @GetMapping
    public List<UserAddressDTO> getAllAddresses() {
        return userAddressService.getAllAddresses();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserAddressDTO> getAddressById(@PathVariable Long id) {
        Optional<UserAddressDTO> address = userAddressService.getAddressById(id);
        return address.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public List<UserAddressDTO> getAddressesByUserId(@PathVariable Long userId) {
        return userAddressService.getAddressesByUserId(userId);
    }

    @GetMapping("/user/{userId}/default")
    public ResponseEntity<UserAddressDTO> getDefaultAddressByUserId(@PathVariable Long userId) {
        Optional<UserAddressDTO> address = userAddressService.getDefaultAddressByUserId(userId);
        return address.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public UserAddressDTO createAddress(@RequestBody UserAddress userAddress) {
        return userAddressService.createAddress(userAddress);
    }

    @PutMapping("/{id}")
    public UserAddressDTO updateAddress(@PathVariable Long id, @RequestBody UserAddress userAddress) {
        return userAddressService.updateAddress(id, userAddress);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Long id) {
        userAddressService.deleteAddress(id);
        return ResponseEntity.noContent().build();
    }
}

