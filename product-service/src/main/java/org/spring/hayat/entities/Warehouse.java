package org.spring.hayat.entities;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity

@Data
public class Warehouse extends BaseEntity {
    private String name;
    private String location;
}
