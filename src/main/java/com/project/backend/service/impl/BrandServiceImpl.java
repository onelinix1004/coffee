package com.project.backend.service.impl;

import com.project.backend.dto.BrandDTO;
import com.project.backend.repository.BrandRepository;
import com.project.backend.service.BrandService;
import com.project.backend.entity.Brand;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandRepository brandRepository;

    /**
     * Retrieves all brands from the database.
     *
     * @return A list of BrandDTO objects representing all brands in the database.
     */
    @Override
    public List<BrandDTO> getAllBrands() {
        return brandRepository.findAll().stream()
                .map(this::convertToDTO) // Convert each Brand entity to a BrandDTO
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a brand by its ID.
     *
     * @param id The ID of the brand to be retrieved.
     * @return An Optional containing a BrandDTO if the brand exists, or an empty Optional if not.
     */
    @Override
    public Optional<BrandDTO> getBrandById(Long id) {
        // Find the brand by ID and convert it to a BrandDTO if present
        return brandRepository.findById(id).map(this::convertToDTO);
    }

    /**
     * Creates a new brand in the database.
     *
     * @param brand The brand information to be added to the database.
     * @return A BrandDTO object representing the newly created brand.
     */
    @Override
    public BrandDTO createBrand(Brand brand) {
        // Save the new brand to the database
        Brand savedBrand = brandRepository.save(brand);
        // Convert the saved entity to a BrandDTO
        return convertToDTO(savedBrand);
    }

    /**
     * Updates an existing brand in the database.
     *
     * @param id           The ID of the brand to be updated.
     * @param updatedBrand The brand information to be updated in the database.
     * @return A BrandDTO object representing the updated brand.
     * @throws RuntimeException if the brand with the specified ID is not found.
     */
    @Override
    public BrandDTO updateBrand(Long id, Brand updatedBrand) {
        // Find the brand by ID or throw a runtime exception if not found
        Brand brand = brandRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Brand not found"));

        // Update the brand's fields with the new information
        brand.setName(updatedBrand.getName());

        // Save the updated brand to the database
        Brand savedBrand = brandRepository.save(brand);

        // Convert the saved entity to a BrandDTO
        return convertToDTO(savedBrand);
    }

    /**
     * Deletes a brand from the database.
     *
     * @param id The ID of the brand to be deleted.
     */
    @Override
    public void deleteBrand(Long id) {
        // Delete the brand by ID from the repository
        brandRepository.deleteById(id);
    }

    /**
     * Converts a Brand entity to a BrandDTO object.
     *
     * @param brand The Brand entity to be converted.
     * @return A BrandDTO object representing the converted Brand entity.
     */
    private BrandDTO convertToDTO(Brand brand) {
        return BrandDTO.builder()
                .id(brand.getId())
                .name(brand.getName())
                .build();
    }
}

