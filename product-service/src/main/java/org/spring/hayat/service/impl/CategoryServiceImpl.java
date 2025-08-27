package org.spring.hayat.service.impl;



import lombok.RequiredArgsConstructor;
import org.spring.hayat.dto.CategoryRequest;
import org.spring.hayat.dto.CategoryResponse;
import org.spring.hayat.entities.Category;
import org.spring.hayat.exception.BusinessException;
import org.spring.hayat.repositories.CategoryRepository;
import org.spring.hayat.service.CategoryService;
import org.spring.hayat.utils.ApiCode;
import org.spring.hayat.utils.ServiceConstant;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    @Transactional
    public Category addCategory(CategoryRequest request) {
        Category parentCategory = null;
        if (categoryRepository.existsByName(request.getName())) {
            throw new BusinessException(ApiCode.CONFLICT.getCode(), ApiCode.CONFLICT.getMessage(), ServiceConstant.CATEGORY);
        }
        if (!ObjectUtils.isEmpty(request) && request.getParentId() != null) {
            parentCategory = categoryRepository.findById(request.getParentId())
                    .orElseThrow(() -> new BusinessException(ApiCode.NOT_FOUND.getCode(), ApiCode.NOT_FOUND.getMessage(), ServiceConstant.CATEGORY));
        }
        Category category = new Category();
        category.setName(request.getName());
        category.setDescription(request.getDescription());
        category.setParent(parentCategory);


        return categoryRepository.save(category);
    }

    @Override
    @Transactional(readOnly = true)
    public Category findByName(String name) {

        return categoryRepository.findByName(name)
                .orElseThrow(() -> new BusinessException(ApiCode.NOT_FOUND.getCode(), ApiCode.NOT_FOUND.getMessage(), ServiceConstant.CATEGORY));
    }

    @Override
    @Transactional(readOnly = true)
    public Category findById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new BusinessException(ApiCode.NOT_FOUND.getCode(), ApiCode.NOT_FOUND.getMessage(), ServiceConstant.CATEGORY));
    }

    @Override
    public Category updateCategory(String name, CategoryRequest request) {
        Category category = categoryRepository.findByName(name).orElseThrow(() -> new BusinessException
                (ApiCode.NOT_FOUND.getCode(), ApiCode.NOT_FOUND.getMessage(), ServiceConstant.CATEGORY));
        category.setName(request.getName());
        category.setDescription(request.getDescription());
        return categoryRepository.save(category);

    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoryResponse> findAll() {
        return categoryRepository.findAllParentCategories();
    }


}
