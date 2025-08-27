package com.boot.user.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "users")
public class Users extends BaseEntity {
    @Column(unique = true, nullable = false)
    private String username;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(unique = true, nullable = false)
    private String password;
    private String gender;
    private String mobileNo;
    @Column(length = 1, nullable = false)
    private String active;
    private String tenantId;
    @ManyToOne(optional = false)
    @JoinColumn(name = "role_id")
    private Roles role;


}
