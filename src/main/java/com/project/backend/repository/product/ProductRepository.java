package com.project.backend.repository.product;

import com.project.backend.entity.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    /**
     * Finds a product by its slug.
     *
     * @param slug The slug of the product to search for.
     * @return An Optional containing the product if found, or an empty Optional if not.
     */
    Optional<Product> findBySlug(String slug);
}
