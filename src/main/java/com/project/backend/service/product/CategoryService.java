package com.project.backend.service.product;


import com.project.backend.dto.product.CategoryDTO;
import com.project.backend.entity.product.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    /**
     * Retrieves all categories from the database.
     *
     * @return A list of CategoryDTO objects, which represent all categories in the database.
     */
    List<CategoryDTO> getAllCategories();

    /**
     * Retrieves a category by its ID.
     *
     * @param id The ID of the category to be retrieved.
     * @return An Optional containing a CategoryDTO if the category exists, or an empty Optional if not.
     */
    Optional<CategoryDTO> getCategoryById(Long id);

    /**
     * Creates a new category in the database.
     *
     * @param category The category information to be added to the database.
     * @return A CategoryDTO object representing the newly created category.
     */
    CategoryDTO createCategory(Category category);

    /**
     * Updates an existing category in the database.
     *
     * @param id       The ID of the category to be updated.
     * @param category The category information to be updated in the database.
     * @return A CategoryDTO object representing the updated category.
     */
    CategoryDTO updateCategory(Long id, Category category);

    /**
     * Deletes a category from the database.
     *
     * @param id The ID of the category to be deleted.
     */
    void deleteCategory(Long id);
}
