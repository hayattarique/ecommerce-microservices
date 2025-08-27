package org.spring.hayat.entities;


import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.spring.hayat.utils.ProductStatus;

@EqualsAndHashCode(callSuper = true)
@Data
@RequiredArgsConstructor
@Entity
public class Product extends BaseEntity {
    private String name;
    private String description;
    @Column(unique = true, nullable = false)
    private String sku;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id")
    @ToString.Exclude
    private Category category;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "brand_id")
    @ToString.Exclude
    private Brand brand;
    @Enumerated(EnumType.STRING)
    private ProductStatus productStatus;


}
