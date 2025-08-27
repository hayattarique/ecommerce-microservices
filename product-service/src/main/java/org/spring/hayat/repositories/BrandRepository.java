package org.spring.hayat.repositories;


import org.spring.hayat.dto.BrandDto;
import org.spring.hayat.entities.Brand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BrandRepository extends JpaRepository<Brand, Integer> {
    @Query("SELECT new org.spring.hayat.dto.BrandDto( b.name,b.description) FROM Brand b WHERE b.status = 'A' ")
    Page<BrandDto> findAllActiveBrands(Pageable pageable);

    @Query("SELECT new org.spring.hayat.dto.BrandDto(b.name, b.description) FROM Brand b WHERE LOWER( b.name) LIKE LOWER( :name) AND b.status = 'A' ")
    List<BrandDto> findByName(String name);
}
