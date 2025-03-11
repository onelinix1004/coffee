package com.project.coffee.repository;

import com.project.coffee.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StoreRepository extends JpaRepository<Store, Long> {
    Optional<Store> findByName(String name);
    List<Store> findByIsActive(boolean isActive);
    List<Store> findByLocationContainingIgnoreCase(String location);
    boolean existsStoreById(Long id);

}
