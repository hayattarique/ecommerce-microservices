package org.spring.hayat.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class StockMovement extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @ManyToOne
    @JoinColumn(name = "warehouse_id")
    private Warehouse warehouse;
    private Integer quantity;
    private String movementType; // e.g., "IN", "OUT", "ADJUSTMENT"
    private String reason; // e.g., "Stock Adjustment", "Order Fulfillment",
}
