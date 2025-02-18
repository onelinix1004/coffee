package com.project.backend.service;


import com.project.backend.dto.BrandDTO;
import com.project.backend.entity.Brand;

import java.util.List;
import java.util.Optional;

public interface BrandService {
    /**
     * Retrieves all brands from the database.
     *
     * @return A list of BrandDTO objects representing all brands in the database.
     */
    List<BrandDTO> getAllBrands();

    /**
     * Retrieves a brand by their ID.
     *
     * @param id The ID of the brand to be retrieved.
     * @return An Optional containing a BrandDTO if the brand exists, or an empty Optional if not.
     */
    Optional<BrandDTO> getBrandById(Long id);

    /**
     * Creates a new brand in the database.
     *
     * @param brand The brand information to be added to the database.
     * @return A BrandDTO object representing the newly created brand.
     */
    BrandDTO createBrand(Brand brand);

    /**
     * Updates an existing brand in the database.
     *
     * @param id    The ID of the brand to be updated.
     * @param brand The brand information to be updated in the database.
     * @return A BrandDTO object representing the updated brand.
     */
    BrandDTO updateBrand(Long id, Brand brand);

    /**
     * Deletes a brand from the database.
     *
     * @param id The ID of the brand to be deleted.
     */
    void deleteBrand(Long id);
}


