package com.boot.user.aspect;

import com.boot.user.annotation.Audit;
import com.boot.user.config.JwtAuthenticationToken;
import com.boot.user.entities.AuditLog;
import com.boot.user.repositories.AuditLogsRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@Aspect
@RequiredArgsConstructor
@Slf4j
public class AuditAspect {
    private final AuditLogsRepository auditLogsRepository;
    private final HttpServletRequest request;

    @Around("@annotation(com.boot.user.annotation.Audit)")
    public Object logAuditEvent(ProceedingJoinPoint joinPoint) throws IllegalAccessException {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Audit audit = signature.getMethod().getAnnotation(Audit.class);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String tenantId = (authentication instanceof JwtAuthenticationToken jwtAuth) ? jwtAuth.getTenantId() : "UNKNOWN";
        String deviceId = (authentication instanceof JwtAuthenticationToken jwtAuth) ? jwtAuth.getDeviceId() : "UNKNOWN";
        String username = (authentication != null) ? authentication.getName() : "ANONYMOUS";

        String ip = request.getRemoteAddr();
        String requestMethod = request.getMethod();
        String uri = request.getRequestURI();
        Object result = null;
        boolean success = true;
        try {
            result = joinPoint.proceed();
            return result;
        } catch (Throwable e) {
            success = false;
            throw new IllegalAccessException("Error during method execution: " + e.getMessage());
        } finally {

            AuditLog auditLog = new AuditLog();
            auditLog.setSuccess(success);
            auditLog.setIp(ip);
            auditLog.setAction(audit.action());
            auditLog.setUsername(username);
            auditLog.setTenantId(tenantId);
            auditLog.setDeviceId(deviceId);

            auditLog.setDescription(audit.description() + " | URI: " + uri + " | METHOD: " + requestMethod);
            auditLogsRepository.save(auditLog);
            log.info("AUDIT - User: {}, Action: {}, Success: {}, IP: {}, Tenant: {}, Device: {}, URI: {}, METHOD: {}", username, audit.action(), success, ip, tenantId, deviceId, uri, requestMethod);

        }
    }
}
