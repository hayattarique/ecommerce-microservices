package com.boot.user.repositories;

import com.boot.user.entities.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditLogsRepository extends JpaRepository<AuditLog, Long> {

}
