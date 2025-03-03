package com.project.coffee.dto;

import lombok.*;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {

    private Integer userId;
    private String username;
    private String email;
    private String password;
    private String role;
    private Timestamp createdAt;
    private Integer loyaltyPoints;
}