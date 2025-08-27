package com.boot.user.dto;

public record TokenRefreshRequest(String refreshToken,String deviceId) {
}
