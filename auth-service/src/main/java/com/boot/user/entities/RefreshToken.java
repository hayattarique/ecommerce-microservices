package com.boot.user.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class RefreshToken extends BaseEntity {

    @Column(unique = true, nullable = false)
    private String token;
    @Column(nullable = false)
    private LocalDateTime expiredAt;
    @Column(nullable = false)
    private boolean revoked;
    @Version
    private int version;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    private Users user;

}
