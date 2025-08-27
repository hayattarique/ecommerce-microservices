package org.spring.hayat.controller;

import lombok.RequiredArgsConstructor;
import org.spring.hayat.dto.ApiResponse;
import org.spring.hayat.dto.CategoryRequest;
import org.spring.hayat.dto.CategoryResponse;
import org.spring.hayat.entities.Category;
import org.spring.hayat.service.CategoryService;
import org.spring.hayat.utils.ApiCode;
import org.spring.hayat.utils.ApiResponseBuilder;
import org.spring.hayat.utils.MappingConstant;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(MappingConstant.CategoryMapping.BASE)
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    private final ApiResponseBuilder response;

    @PostMapping(MappingConstant.CategoryMapping.CREATE)
    public ResponseEntity<ApiResponse<Category>> addCategory(@RequestBody CategoryRequest request) {
        return response.ok(categoryService.addCategory(request), ApiCode.CREATED.getCode(), ApiCode.CREATED.getMessage());
    }

    @GetMapping(MappingConstant.CategoryMapping.GET_BY_NAME)
    public ResponseEntity<ApiResponse<Category>> getCategory(@PathVariable String name) {
        return response.ok(categoryService.findByName(name), ApiCode.OK.getCode(), ApiCode.OK.getMessage());
    }

    @GetMapping(MappingConstant.CategoryMapping.GET_BY_ID)
    public ResponseEntity<ApiResponse<Category>> getCategory(@PathVariable Long id) {
        return response.ok(categoryService.findById(id), ApiCode.OK.getCode(), ApiCode.OK.getMessage());
    }

    @PutMapping(MappingConstant.CategoryMapping.UPDATE_BY_NAME)
    public ResponseEntity<ApiResponse<Category>> updateCategory(@PathVariable String name, @RequestBody CategoryRequest request) {
        return response.ok(categoryService.updateCategory(name, request), ApiCode.OK.getCode(), ApiCode.OK.getMessage());
    }

    @GetMapping(MappingConstant.CategoryMapping.FIND_ALL)
    ResponseEntity<ApiResponse<List<CategoryResponse>>> findAll() {
        return response.ok(categoryService.findAll(), ApiCode.OK.getCode(), ApiCode.OK.getMessage());
    }
}
