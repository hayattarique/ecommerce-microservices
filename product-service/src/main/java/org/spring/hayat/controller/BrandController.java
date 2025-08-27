package org.spring.hayat.controller;


import lombok.RequiredArgsConstructor;
import org.spring.hayat.dto.ApiResponse;
import org.spring.hayat.dto.BrandDto;
import org.spring.hayat.service.BrandService;
import org.spring.hayat.utils.ApiCode;
import org.spring.hayat.utils.ApiResponseBuilder;
import org.spring.hayat.utils.MappingConstant;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(MappingConstant.BrandMapping.BASE)
public class BrandController {

    private final BrandService brandService;
    private final ApiResponseBuilder response;

    @PostMapping(MappingConstant.BrandMapping.CREATE)
    public ResponseEntity<ApiResponse<Boolean>> addBrands(@RequestBody BrandDto brandDto) {
        return response.ok(brandService.createBrand(brandDto), ApiCode.CREATED.getCode(), ApiCode.CREATED.getMessage());
    }

    @PostMapping(MappingConstant.BrandMapping.REGISTER)
    public ResponseEntity<ApiResponse<Boolean>> registerBrands(@RequestBody List<BrandDto> dtoList) {

        return response.ok(brandService.registerBrand(dtoList), ApiCode.CREATED.getCode(), ApiCode.CREATED.getMessage());
    }

    @GetMapping(MappingConstant.BrandMapping.FIND_ALL)
    public ResponseEntity<ApiResponse<List<BrandDto>>> findAllBrands(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Page<BrandDto> brands = brandService.findAllBrands(page, size);
        return response.okWithPagination(brands.getContent(), ApiCode.OK.getCode(), ApiCode.OK.getMessage(), brands);
    }

    @GetMapping(MappingConstant.BrandMapping.SEARCH)
    public ResponseEntity<ApiResponse<List<BrandDto>>> searchByBrandName(@RequestParam String search) {

        return response.ok(brandService.findByBrandName(search), ApiCode.OK.getCode(), ApiCode.OK.getMessage());
    }


}
