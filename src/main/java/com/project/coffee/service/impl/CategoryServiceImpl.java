package com.project.coffee.service.impl;

import com.project.coffee.dto.CategoryDTO;
import com.project.coffee.entity.Category;
import com.project.coffee.exception.BadRequestException;
import com.project.coffee.exception.ResourceNotFoundException;
import com.project.coffee.repository.CategoryRepository;
import com.project.coffee.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    /**
     * Retrieves a list of all categories from the database.
     *
     * @return a list of CategoryDTO objects representing all categories
     */
    @Override
    public List<CategoryDTO> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a category by its ID from the database.
     *
     * @param categoryId the ID of the category to retrieve
     * @return an Optional containing the CategoryDTO object if found, empty otherwise
     */
    @Override
    public Optional<CategoryDTO> getCategoryById(Integer categoryId) {
        return categoryRepository.findById(categoryId)
                .map(this::convertToDTO);
    }

    /**
     * Creates a new category and stores it in the database.
     *
     * @param categoryDTO the CategoryDTO to create a category from
     * @return a CategoryDTO object representing the newly created category
     * @throws BadRequestException if the category creation fails
     */
    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        if (categoryDTO.getName() == null || categoryDTO.getName().trim().isEmpty()) {
            throw new BadRequestException("Category name is required");
        }
        Category category = convertToEntity(categoryDTO);
        Category savedCategory = categoryRepository.save(category);
        return convertToDTO(savedCategory);
    }

    /**
     * Updates an existing category in the database with the provided details.
     *
     * @param categoryId the ID of the category to update
     * @param categoryDTO the updated category details
     * @return a CategoryDTO object representing the updated category
     * @throws ResourceNotFoundException if the category with the given ID is not found
     */
    @Override
    public CategoryDTO updateCategory(Integer categoryId, CategoryDTO categoryDTO) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with ID: " + categoryId));
        category.setName(categoryDTO.getName());
        category.setParent(convertToEntity(categoryDTO));
        Category updatedCategory = categoryRepository.save(category);
        return convertToDTO(updatedCategory);
    }

    /**
     * Deletes a category from the database by its ID.
     *
     * @param categoryId the ID of the category to delete
     * @throws ResourceNotFoundException if the category with the given ID is not found
     */
    @Override
    public void deleteCategory(Integer categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with ID: " + categoryId));
        categoryRepository.delete(category);
    }

    /**
     * Converts a Category entity to a CategoryDTO object.
     *
     * @param category the Category entity to convert
     * @return a CategoryDTO object representing the given Category entity
     */
    private CategoryDTO convertToDTO(Category category) {
        CategoryDTO dto = new CategoryDTO();
        dto.setCategoryId(category.getCategoryId());
        dto.setName(category.getName());
        dto.setParentId(category.getParent().getCategoryId());
        dto.setCreatedAt(category.getCreatedAt());
        return dto;
    }

    /**
     * Converts a CategoryDTO object to a Category entity.
     *
     * @param dto the CategoryDTO object to convert
     * @return a Category entity representing the given CategoryDTO
     */
    private Category convertToEntity(CategoryDTO dto) {
        Category category = new Category();
        category.setName(dto.getName());
        category.setParent(categoryRepository.findById(dto.getParentId()).orElse(null));
        return category;
    }
}