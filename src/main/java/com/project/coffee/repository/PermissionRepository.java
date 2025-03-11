package com.project.coffee.repository;

import com.project.coffee.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
    Optional<Permission> findByName(String name);
    List<Permission> findByIsActive(boolean isActive);
    List<Permission> findByResourceAndAction(String resource, String action);
}
