package org.spring.hayat.service.impl;


import lombok.RequiredArgsConstructor;
import org.spring.hayat.dto.BrandDto;
import org.spring.hayat.entities.Brand;
import org.spring.hayat.repositories.BrandRepository;
import org.spring.hayat.service.BrandService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {
    private final BrandRepository brandRepository;

    @Override
    @Transactional
    public Boolean createBrand(BrandDto brandDto) {
        Brand brand = new Brand();
        BeanUtils.copyProperties(brandDto, brand);
        brandRepository.save(brand);
        return brand.getId() != null;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<BrandDto> findAllBrands(int page, int size) {
        return brandRepository.findAllActiveBrands(PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "name")));
    }

    @Override
    @Transactional(readOnly = true)
    public List<BrandDto> findByBrandName(String search) {
        return brandRepository.findByName("%" + search + "%");
    }

    @Override
    @Transactional
    public Boolean registerBrand(List<BrandDto> dtoList) {
        List<Brand> list = new ArrayList<>();
        for (BrandDto brandDto : dtoList) {
            Brand brand = new Brand();
            BeanUtils.copyProperties(brandDto, brand);
            list.add(brand);
        }
        List<Brand> brands = brandRepository.saveAll(list);
        return brands.size() == dtoList.size();
    }

}
