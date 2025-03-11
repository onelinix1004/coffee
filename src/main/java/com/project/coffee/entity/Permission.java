package com.project.coffee.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "tbl_permission")
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "permission_id")
    Long id;

    String name;
    String description;
    String resource;
    String action;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    boolean isActive;

    @ManyToMany(mappedBy = "permissions")
    Set<Role> roles;
}
