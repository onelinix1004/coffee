package com.project.coffee.service.impl;

import com.project.coffee.dto.StoreDTO;
import com.project.coffee.entity.store.Store;
import com.project.coffee.exception.DuplicateResourceException;
import com.project.coffee.exception.ResourceNotFoundException;
import com.project.coffee.repository.StoreRepository;
import com.project.coffee.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.project.coffee.dto.StoreDTO.convertToEntity;


@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {

    @Autowired
    private StoreRepository storeRepository;


    public StoreDTO createStore(StoreDTO storeDTO) {
        if (storeRepository.existsStoreById(storeDTO.getId())) {
            throw new DuplicateResourceException("Store exists");
        }
        Store store = convertToEntity(storeDTO);
        storeRepository.save(store);
        return convertToDTO(store);
    }


    public StoreDTO getStoreById(Long id) {
        Store store = storeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Store not found"));
        return convertToDTO(store);
    }

    public List<StoreDTO> getAllStores() {
        List<Store> stores = storeRepository.findAll();
        return stores.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public StoreDTO updateStore(Long id, StoreDTO storeDTO) {
        Store store = storeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Store not found"));
        store = convertToEntity(storeDTO);
        storeRepository.save(store);
        return convertToDTO(store);
    }

    public void deleteStore(Long id) {
        Store store = storeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Store not found"));
        storeRepository.delete(store);
    }

    private StoreDTO convertToDTO(Store store) {
        return StoreDTO.builder()
                .id(store.getId())
                .name(store.getName())
                .location(store.getLocation())
                .phoneNumber(store.getPhoneNumber())
                .email(store.getEmail())
                .isActive(store.isActive())
                .createdAt(store.getCreatedAt())
                .updatedAt(store.getUpdatedAt())
                .build();
    }
}
