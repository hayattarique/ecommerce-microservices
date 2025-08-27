package org.spring.hayat.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Review extends BaseEntity {
    private int rating;
    private String comment;
    @ManyToOne(optional = false)
    @JoinColumn(name = "product_id")
    private Product product;
}
