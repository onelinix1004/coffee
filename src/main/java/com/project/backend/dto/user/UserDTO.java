package com.project.backend.dto.user;

import com.project.backend.entity.user.User;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    private Long id;
    private String username;
    private String email;
    private String phone;
    private String address;
    private User.Role role;
    private String avatarUrl;
    private Boolean isActive;
}
