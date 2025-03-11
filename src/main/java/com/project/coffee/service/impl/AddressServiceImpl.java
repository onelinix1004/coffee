package com.project.coffee.service.impl;

import com.project.coffee.dto.AddressDTO;
import com.project.coffee.entity.Address;
import com.project.coffee.entity.Store;
import com.project.coffee.entity.User;
import com.project.coffee.exception.ResourceNotFoundException;
import com.project.coffee.repository.AddressRepository;
import com.project.coffee.repository.StoreRepository;
import com.project.coffee.repository.UserRepository;
import com.project.coffee.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;

    @Override
    public AddressDTO createAddress(AddressDTO addressDTO) {
        User user = null;
        Store store = null;

        if (addressDTO.getUserId() != null) {
            user = userRepository.findById(addressDTO.getUserId())
                    .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        }

        if (addressDTO.getStoreId() != null) {
            store = storeRepository.findById(addressDTO.getStoreId())
                    .orElseThrow(() -> new ResourceNotFoundException("Store not found"));
        }


        Address address = AddressDTO.convertToEntity(addressDTO);
        address.setUser(user);
        address.setStore(store);
        address.setCreatedAt(LocalDateTime.now());
        address.setUpdatedAt(LocalDateTime.now());

        Address savedAddress = addressRepository.save(address);
        return convertToDTO(savedAddress);
    }

    @Override
    public AddressDTO getAddressById(Long id) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Address not found"));
        return convertToDTO(address);
    }

    @Override
    public List<AddressDTO> getAllAddresses() {
        return addressRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AddressDTO updateAddress(Long id, AddressDTO addressDTO) {
        Address existingAddress = addressRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Address not found"));

        existingAddress.setStreet(addressDTO.getStreet());
        existingAddress.setCity(addressDTO.getCity());
        existingAddress.setPostalCode(addressDTO.getPostalCode());
        existingAddress.setAddressType(addressDTO.getAddressType());
        existingAddress.setDefault(addressDTO.isDefault());
        existingAddress.setUpdatedAt(LocalDateTime.now());

        Address updatedAddress = addressRepository.save(existingAddress);
        return convertToDTO(updatedAddress);
    }

    @Override
    public void deleteAddress(Long id) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Address not found"));
        addressRepository.delete(address);
    }

    private AddressDTO convertToDTO(Address address) {
        return AddressDTO.builder()
                .id(address.getId())
                .street(address.getStreet())
                .city(address.getCity())
                .postalCode(address.getPostalCode())
                .addressType(address.getAddressType())
                .isDefault(address.isDefault())
                .createdAt(address.getCreatedAt())
                .updatedAt(address.getUpdatedAt())
                .userId(address.getUser() != null ? address.getUser().getId() : null)
                .storeId(address.getStore() != null ? address.getStore().getId() : null)
                .build();
    }
}
