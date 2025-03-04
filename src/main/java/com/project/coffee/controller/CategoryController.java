package com.project.coffee.controller;

import com.project.coffee.service.CategoryService;
import com.project.coffee.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * Retrieves a list of all categories from the database.
     *
     * @return a ResponseEntity containing a list of CategoryDTO objects representing all categories
     */
    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        List<CategoryDTO> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    /**
     * Retrieves a category by its ID from the database.
     *
     * @param categoryId the ID of the category to retrieve
     * @return a ResponseEntity containing the CategoryDTO with the given ID if found,
     *         or a 404 response if not found
     */
    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Integer categoryId) {
        return categoryService.getCategoryById(categoryId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(404).body(null));
    }

    /**
     * Creates a new category and stores it in the database.
     *
     * @param categoryDTO the CategoryDTO to create a category from
     * @return a ResponseEntity containing the newly created CategoryDTO,
     *         or a 400 response if the category creation fails
     */
    @PostMapping
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO categoryDTO) {
        try {
            CategoryDTO createdCategory = categoryService.createCategory(categoryDTO);
            return ResponseEntity.status(201).body(createdCategory);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    /**
     * Updates an existing category with the provided details.
     *
     * @param categoryId the ID of the category to update
     * @param categoryDTO the CategoryDTO containing the updated category details
     * @return a ResponseEntity containing the updated CategoryDTO if found,
     *         or a 404 response if the category is not found
     */
    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable Integer categoryId, @RequestBody CategoryDTO categoryDTO) {
        try {
            CategoryDTO updatedCategory = categoryService.updateCategory(categoryId, categoryDTO);
            return ResponseEntity.ok(updatedCategory);
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(404).body(null);
        }
    }

    /**
     * Deletes a category from the database by its ID.
     *
     * @param categoryId the ID of the category to delete
     * @return a ResponseEntity with no content if the deletion is successful,
     *         or a 404 response if the category with the given ID does not exist
     */
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Integer categoryId) {
        try {
            categoryService.deleteCategory(categoryId);
            return ResponseEntity.ok().build();
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(404).build();
        }
    }
}