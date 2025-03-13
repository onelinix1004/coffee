package com.project.coffee.repository;

import com.project.coffee.entity.user.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Long> {
    List<Address> findByUserId(Long userId);
    List<Address> findByStoreId(Long storeId);
    Optional<Address> findByUserIdAndIsDefaultTrue(Long userId);
}
