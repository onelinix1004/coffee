package com.project.coffee.repository;

import com.project.coffee.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByParentCategoryId(Long parentCategoryId);
    List<Category> findByIsActive(boolean isActive);
    List<Category> findByNameContainingIgnoreCase(String name);

    boolean existsByName(String name);
}
