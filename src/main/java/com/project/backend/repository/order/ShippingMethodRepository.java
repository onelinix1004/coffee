package com.project.backend.repository.order;

import com.project.backend.entity.order.ShippingMethod;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShippingMethodRepository extends JpaRepository<ShippingMethod,Long>{
}
