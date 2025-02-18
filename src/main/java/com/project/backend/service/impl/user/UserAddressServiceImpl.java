package com.project.backend.service.impl.user;

import com.project.backend.dto.user.UserAddressDTO;
import com.project.backend.repository.user.UserAddressRepository;
import com.project.backend.service.user.UserAddressService;
import com.project.backend.entity.user.UserAddress;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserAddressServiceImpl implements UserAddressService {

    private final UserAddressRepository userAddressRepository;

    @Override
    public List<UserAddressDTO> getAllAddresses() {
        return userAddressRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UserAddressDTO> getAddressById(Long id) {
        return userAddressRepository.findById(id).map(this::convertToDTO);
    }

    @Override
    public List<UserAddressDTO> getAddressesByUserId(Long userId) {
        return userAddressRepository.findByUserId(userId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UserAddressDTO> getDefaultAddressByUserId(Long userId) {
        return userAddressRepository.findByUserIdAndIsDefaultTrue(userId)
                .map(this::convertToDTO);
    }

    @Override
    public UserAddressDTO createAddress(UserAddress userAddress) {
        // Nếu là địa chỉ mặc định, bỏ tất cả địa chỉ mặc định trước đó
        if (userAddress.isDefault()) {
            List<UserAddress> userAddresses = userAddressRepository.findByUserId(userAddress.getUser().getId());
            userAddresses.forEach(addr -> addr.setDefault(false));
            userAddressRepository.saveAll(userAddresses);
        }

        UserAddress savedAddress = userAddressRepository.save(userAddress);
        return convertToDTO(savedAddress);
    }

    @Override
    public UserAddressDTO updateAddress(Long id, UserAddress updatedAddress) {
        UserAddress address = userAddressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Address not found"));

        address.setAddress(updatedAddress.getAddress());
        address.setDefault(updatedAddress.isDefault());

        // Nếu đặt làm mặc định, cập nhật các địa chỉ khác
        if (updatedAddress.isDefault()) {
            List<UserAddress> userAddresses = userAddressRepository.findByUserId(address.getUser().getId());
            userAddresses.forEach(addr -> addr.setDefault(false));
            userAddressRepository.saveAll(userAddresses);
        }

        UserAddress savedAddress = userAddressRepository.save(address);
        return convertToDTO(savedAddress);
    }

    @Override
    public void deleteAddress(Long id) {
        userAddressRepository.deleteById(id);
    }

    private UserAddressDTO convertToDTO(UserAddress address) {
        return UserAddressDTO.builder()
                .id(address.getId())
                .userId(address.getUser().getId())
                .address(address.getAddress())
                .isDefault(address.isDefault())
                .build();
    }
}

