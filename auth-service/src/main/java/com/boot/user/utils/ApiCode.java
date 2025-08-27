package com.boot.user.utils;

import lombok.Getter;

@Getter
public enum ApiCode {

    // --- 2xx Success ---
    OK("200", "Request processed successfully"),
    CREATED("201", "Resource created successfully"),
    UPDATED("202", "Resource updated successfully"),
    DELETED("203", "Resource deleted successfully"),

    // --- 4xx Client Errors ---
    BAD_REQUEST("400", "Invalid request"),
    UNAUTHORIZED("401", "Unauthorized"),
    FORBIDDEN("403", "Forbidden"),
    NOT_FOUND("404", "Resource not found"),
    CONFLICT("409", "Conflict / Duplicate resource"),
    VALIDATION_FAILED("422", "Validation failed"),

    // --- 5xx Server Errors ---
    INTERNAL_SERVER_ERROR("500", "Unexpected server error"),
    SERVICE_UNAVAILABLE("503", "Service unavailable");

    private final String code;
    private final String message;

    ApiCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
