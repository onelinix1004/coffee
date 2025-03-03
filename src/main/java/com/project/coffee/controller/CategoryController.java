package com.project.coffee.controller;


import com.project.coffee.dto.CategoryDTO;
import com.project.coffee.exception.BadRequestException;
import com.project.coffee.exception.ResourceNotFoundException;
import com.project.coffee.service.impl.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryServiceImpl categoryServiceImpl;

    /**
     * Retrieves a list of all categories.
     *
     * @return a ResponseEntity containing a list of CategoryDTO objects.
     */
    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        List<CategoryDTO> categories = categoryServiceImpl.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    /**
     * Retrieves a category by its ID.
     *
     * @param categoryId the ID of the category to be retrieved.
     * @return a ResponseEntity containing a CategoryDTO object.
     * @throws ResourceNotFoundException if no category exists with the given ID.
     */
    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Integer categoryId) {
        try {
            CategoryDTO category = categoryServiceImpl.getCategoryById(categoryId);
            return ResponseEntity.ok(category);
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(404).body(null); // Hoặc trả về lỗi JSON
        }
    }

    /**
     * Creates a new category with the given details.
     *
     * @param categoryDTO a CategoryDTO object containing the details of the category to be created.
     * @return a ResponseEntity containing the newly created CategoryDTO object.
     * @throws BadRequestException if the name of the category is empty or null.
     */
    @PostMapping
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO categoryDTO) {
        try {
            CategoryDTO createdCategory = categoryServiceImpl.createCategory(categoryDTO);
            return ResponseEntity.status(201).body(createdCategory);
        } catch (BadRequestException ex) {
            return ResponseEntity.badRequest().body(null); // Hoặc trả về lỗi JSON
        }
    }

    /**
     * Updates a category with the given ID using the given details.
     *
     * @param categoryId the ID of the category to be updated.
     * @param categoryDTO a CategoryDTO object containing the details of the category to be updated.
     * @return a ResponseEntity containing the updated CategoryDTO object.
     * @throws ResourceNotFoundException if no category exists with the given ID.
     * @throws BadRequestException if the name of the category is empty or null.
     */
    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable Integer categoryId, @RequestBody CategoryDTO categoryDTO) {
        try {
            CategoryDTO updatedCategory = categoryServiceImpl.updateCategory(categoryId, categoryDTO);
            return ResponseEntity.ok(updatedCategory);
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(404).body(null);
        } catch (BadRequestException ex) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    /**
     * Deletes a category with the given ID.
     *
     * @param categoryId the ID of the category to be deleted.
     * @return a ResponseEntity with status 200 if the deletion was successful, or 404 if the category does not exist.
     */
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Integer categoryId) {
        try {
            categoryServiceImpl.deleteCategory(categoryId);
            return ResponseEntity.ok().build();
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(404).build();
        }
    }
}