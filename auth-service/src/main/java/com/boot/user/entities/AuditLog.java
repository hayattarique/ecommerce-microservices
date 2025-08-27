package com.boot.user.entities;

import com.boot.user.utils.AuditEventType;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "audit_logs")
public class AuditLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private AuditEventType action;
    private String username;
    @Column(nullable = false)
    private String ip;
    private String tenantId;
    private String deviceId;
    @Column(nullable = false)
    private String description;
    private boolean success;
}
