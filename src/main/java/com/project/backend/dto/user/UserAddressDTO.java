package com.project.backend.dto.user;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserAddressDTO {
    private Long id;
    private Long userId;
    private String address;
    private boolean isDefault;
}

