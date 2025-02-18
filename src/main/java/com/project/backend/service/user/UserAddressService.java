package com.project.backend.service.user;

import com.project.backend.dto.user.UserAddressDTO;
import com.project.backend.entity.user.UserAddress;

import java.util.List;
import java.util.Optional;

public interface UserAddressService {
    List<UserAddressDTO> getAllAddresses();
    Optional<UserAddressDTO> getAddressById(Long id);
    List<UserAddressDTO> getAddressesByUserId(Long userId);
    Optional<UserAddressDTO> getDefaultAddressByUserId(Long userId);
    UserAddressDTO createAddress(UserAddress userAddress);
    UserAddressDTO updateAddress(Long id, UserAddress userAddress);
    void deleteAddress(Long id);
}

