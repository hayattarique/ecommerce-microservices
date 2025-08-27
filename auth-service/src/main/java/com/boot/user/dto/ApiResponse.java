package com.boot.user.dto;

import java.time.LocalDateTime;

/**
 * @param code      e.g. "CAT-200" or "GEN-500"
 * @param message   human-readable
 * @param data      actual payload
 * @param timestamp response time
 * @param isOkay    true/false
 */
public record ApiResponse<T>(String code, String message, T data, LocalDateTime timestamp, boolean isOkay,PaginationInfo paginationInfo) {
}
