package org.spring.hayat.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Data
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CreatedDate
    private Instant createdDate;
    @CreatedBy
    private String createdBy;
    @LastModifiedBy
    private String updatedBy;
    @LastModifiedDate
    private Instant lastModifiedDate;
    private boolean isDeleted = false;
    private Instant deletedAt;
    private String deletedBy;
    @Column(length = 1)
    private String status = "A";

    @PreRemove
    public void softDelete() {
        this.isDeleted = true;
        this.deletedAt = Instant.now();
        this.deletedBy = this.updatedBy;
    }
}
