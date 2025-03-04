package com.project.coffee.service;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    /**
     * Retrieves a list of all categories from the database.
     *
     * @return a list of CategoryDTO objects representing all categories
     */
    List<CategoryDTO> getAllCategories();

    /**
     * Retrieves a category by its ID from the database.
     *
     * @param categoryId the ID of the category to retrieve
     * @return an Optional containing the CategoryDTO object if found, empty otherwise
     */
    Optional<CategoryDTO> getCategoryById(Integer categoryId);

    /**
     * Creates a new category and stores it in the database.
     *
     * @param categoryDTO the CategoryDTO to create a category from
     * @return a CategoryDTO object representing the newly created category
     * @throws com.project.coffee.exception.BadRequestException if the category creation fails
     */
    CategoryDTO createCategory(CategoryDTO categoryDTO);

    /**
     * Updates an existing category in the database with the provided details.
     *
     * @param categoryId the ID of the category to update
     * @param categoryDTO the updated category details
     * @return a CategoryDTO object representing the updated category
     * @throws com.project.coffee.exception.ResourceNotFoundException if the category with the given ID is not found
     */
    CategoryDTO updateCategory(Integer categoryId, CategoryDTO categoryDTO);

    /**
     * Deletes a category from the database by its ID.
     *
     * @param categoryId the ID of the category to delete
     * @throws com.project.coffee.exception.ResourceNotFoundException if the category with the given ID is not found
     */
    void deleteCategory(Integer categoryId);
}