package com.boot.user.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Roles {
    public Roles(Long id) {
        this.id = id;
    }

    @Id
    private Long id;

    @Column(nullable = false)
    private String role;
}
