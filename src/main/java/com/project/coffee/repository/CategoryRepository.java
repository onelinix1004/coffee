package com.project.coffee.repository;

import com.project.coffee.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    /**
     * Retrieves a list of categories whose names contain the specified string,
     * ignoring case considerations.
     *
     * @param name the substring to search for within category names.
     * @return a list of categories with names containing the specified substring.
     */
    List<Category> findByNameContainingIgnoreCase(String name);
    /**
     * Retrieves a list of categories whose parentId matches the specified value.
     *
     * @param parentId the parentId to search for within category records.
     * @return a list of categories with the specified parentId.
     */
    List<Category> findByParentId(Integer parentId);
}
