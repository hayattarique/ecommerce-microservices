package org.spring.hayat.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Brand extends BaseEntity {
    @Column(unique = true, nullable = false)
    private String name;
    private String description;

}
