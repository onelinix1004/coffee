package com.project.coffee.dto;

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
    private String website;
    private boolean isActive;
    private Long warehouseAddressId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
