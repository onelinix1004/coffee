package com.project.backend.service.impl.order;

import com.project.backend.dto.order.ShippingMethodDTO;
import com.project.backend.repository.order.ShippingMethodRepository;
import com.project.backend.service.order.ShippingMethodService;
import com.project.backend.entity.order.ShippingMethod;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShippingMethodServiceImpl implements ShippingMethodService {

    private final ShippingMethodRepository shippingMethodRepository;

    @Override
    public List<ShippingMethodDTO> getAllShippingMethods() {
        return shippingMethodRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ShippingMethodDTO> getShippingMethodById(Long id) {
        return shippingMethodRepository.findById(id).map(this::convertToDTO);
    }

    @Override
    public ShippingMethodDTO createShippingMethod(ShippingMethod shippingMethod) {
        ShippingMethod savedShippingMethod = shippingMethodRepository.save(shippingMethod);
        return convertToDTO(savedShippingMethod);
    }

    @Override
    public ShippingMethodDTO updateShippingMethod(Long id, ShippingMethod updatedShippingMethod) {
        ShippingMethod shippingMethod = shippingMethodRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Shipping method not found"));

        shippingMethod.setName(updatedShippingMethod.getName());
        shippingMethod.setCost(updatedShippingMethod.getCost());

        ShippingMethod savedShippingMethod = shippingMethodRepository.save(shippingMethod);
        return convertToDTO(savedShippingMethod);
    }

    @Override
    public void deleteShippingMethod(Long id) {
        shippingMethodRepository.deleteById(id);
    }

    private ShippingMethodDTO convertToDTO(ShippingMethod shippingMethod) {
        return ShippingMethodDTO.builder()
                .id(shippingMethod.getId())
                .name(shippingMethod.getName())
                .cost(shippingMethod.getCost())
                .build();
    }
}

