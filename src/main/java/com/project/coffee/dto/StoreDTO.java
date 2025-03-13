package com.project.coffee.dto;

import com.project.coffee.entity.store.Store;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreDTO {
    private Long id;
    private String name;
    private String location;
    private String phoneNumber;
    private String email;
    private boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    public static Store convertToEntity(StoreDTO storeDTO) {
        return Store.builder()
                .id(storeDTO.getId())
                .name(storeDTO.getName())
                .location(storeDTO.getLocation())
                .phoneNumber(storeDTO.getPhoneNumber())
                .email(storeDTO.getEmail())
                .isActive(storeDTO.isActive())
                .createdAt(storeDTO.getCreatedAt())
                .updatedAt(storeDTO.getUpdatedAt())
                .build();
    }
}
