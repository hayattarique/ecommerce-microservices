package org.spring.hayat.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class PriceHistory extends BaseEntity {
    private Double oldPrice;
    private Double newPrice;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    private String currency = "INR";
}