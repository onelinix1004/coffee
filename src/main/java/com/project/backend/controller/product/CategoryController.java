package com.project.backend.controller.product;

import com.project.backend.dto.product.CategoryDTO;
import com.project.backend.service.product.CategoryService;
import com.project.backend.entity.product.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * Retrieves a list of all categories.
     *
     * @return a list of all categories
     */
    @GetMapping
    public List<CategoryDTO> getAllCategories() {
        return categoryService.getAllCategories();
    }

    /**
     * Retrieves a category by its ID.
     *
     * @param id the ID of the category to be retrieved
     * @return a ResponseEntity containing the category if found, or a 404 NOT FOUND response if the category does not exist
     */
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Long id) {
        // Attempt to find the category by ID using the CategoryService
        Optional<CategoryDTO> category = categoryService.getCategoryById(id);

        // Return the category if found, otherwise return a 404 NOT FOUND response
        return category.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Creates a new category in the database.
     *
     * @param category the new category to be created
     * @return a CategoryDTO object representing the newly created category
     */
    @PostMapping
    public CategoryDTO createCategory(@RequestBody Category category) {
        return categoryService.createCategory(category);
    }

    /**
     * Updates an existing category.
     *
     * @param id the ID of the category to be updated
     * @param category the updated category information
     * @return a CategoryDTO object representing the updated category
     */
    @PutMapping("/{id}")
    public CategoryDTO updateCategory(@PathVariable Long id, @RequestBody Category category) {
        return categoryService.updateCategory(id, category);
    }

    /**
     * Deletes a category by its ID.
     *
     * @param id the ID of the category to be deleted
     * @return a 204 NO CONTENT response if the category is successfully deleted, or a 404 NOT FOUND response if the category does not exist
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        // Attempt to delete the category by ID using the CategoryService
        categoryService.deleteCategory(id);

        // Return a 204 NO CONTENT response if the category is successfully deleted
        return ResponseEntity.noContent().build();
    }
}

