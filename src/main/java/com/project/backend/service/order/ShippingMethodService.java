package com.project.backend.service.order;

import com.project.backend.dto.order.ShippingMethodDTO;
import com.project.backend.entity.order.ShippingMethod;

import java.util.List;
import java.util.Optional;

public interface ShippingMethodService {

    List<ShippingMethodDTO> getAllShippingMethods();
    Optional<ShippingMethodDTO> getShippingMethodById(Long id);
    ShippingMethodDTO createShippingMethod(ShippingMethod shippingMethod);
    ShippingMethodDTO updateShippingMethod(Long id, ShippingMethod shippingMethod);
    void deleteShippingMethod(Long id);
}

