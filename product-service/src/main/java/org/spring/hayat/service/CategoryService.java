package org.spring.hayat.service;



import org.spring.hayat.dto.CategoryRequest;
import org.spring.hayat.dto.CategoryResponse;
import org.spring.hayat.entities.Category;

import java.util.List;

public interface CategoryService {
    /**
     * Adds a new category.
     *
     * @param categoryDto DTO containing category details
     * @return category response with details of the added category
     */
    Category addCategory(CategoryRequest categoryDto);

    /**
     *
     * @param name the name of the category to find
     * @return the category with the specified name
     */
    Category findByName(String name);

    Category findById(Long id);

    Category updateCategory(String name, CategoryRequest request);

    List<CategoryResponse> findAll();
}
