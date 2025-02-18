package com.project.backend.controller;

import com.project.backend.dto.BrandDTO;
import com.project.backend.service.BrandService;
import com.project.backend.entity.Brand;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/brands")
@RequiredArgsConstructor
public class BrandController {

    @Autowired
    private BrandService brandService;

    @GetMapping
    public List<BrandDTO> retrieveAllBrands() {
        return brandService.getAllBrands();
    }

    /**
     * Handles the HTTP GET request to retrieve a brand by its ID.
     *
     * @param id The ID of the brand to be retrieved.
     * @return A ResponseEntity containing a BrandDTO if the brand is found, or a 404 status if not.
     */
    @GetMapping("/{id}")
    public ResponseEntity<BrandDTO> getBrandById(@PathVariable Long id) {
        // Attempt to find the brand by ID using the BrandService
        Optional<BrandDTO> brand = brandService.getBrandById(id);

        // Return the brand if found, otherwise return a 404 Not Found response
        return brand.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Handles the HTTP POST request to create a new brand.
     *
     * @param brand The brand information to be added to the database.
     * @return A BrandDTO object representing the newly created brand.
     */
    @PostMapping
    public BrandDTO createBrand(@RequestBody Brand brand) {
        // Delegate the call to the brandService to create the brand
        return brandService.createBrand(brand);
    }

    /**
     * Handles the HTTP PUT request to update an existing brand.
     *
     * @param id    The ID of the brand to be updated.
     * @param brand The brand information to be updated in the database.
     * @return A BrandDTO object representing the updated brand.
     */
    @PutMapping("/{id}")
    public BrandDTO updateBrand(@PathVariable Long id, @RequestBody Brand brand) {
        // Delegate the call to the brandService to update the brand
        return brandService.updateBrand(id, brand);
    }

    /**
     * Handles the HTTP DELETE request to delete a brand.
     * <p>
     * When this endpoint is called, it will delete the brand with the specified ID.
     *
     * @param id The ID of the brand to be deleted.
     * @return An empty response with a 204 No Content status if the brand is found, or a 404 Not Found response if not.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBrand(@PathVariable Long id) {
        // Attempt to find the brand by ID using the BrandService
        brandService.deleteBrand(id);

        // Return an empty response with a 204 No Content status if the brand was found, or a 404 Not Found response if not
        return ResponseEntity.noContent().build();
    }
}

