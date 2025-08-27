package org.spring.hayat.repositories;

import org.spring.hayat.dto.CategoryResponse;
import org.spring.hayat.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    boolean existsByName(String name);


    Optional<Category> findByName(String name);

    @Query("SELECT new org.spring.hayat.dto.CategoryResponse(c.id,c.name,c.description) FROM Category c WHERE c.parent IS NULL")
    List<CategoryResponse> findAllParentCategories();

}
