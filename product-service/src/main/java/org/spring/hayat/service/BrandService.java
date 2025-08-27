package org.spring.hayat.service;

import org.spring.hayat.dto.BrandDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BrandService {

    Boolean createBrand(BrandDto brandDto);

    Page<BrandDto> findAllBrands(int page, int size);

    List<BrandDto> findByBrandName(String search);

    Boolean registerBrand(List<BrandDto> dtoList);
}
