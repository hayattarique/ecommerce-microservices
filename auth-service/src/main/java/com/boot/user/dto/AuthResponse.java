package com.boot.user.dto;

public record AuthResponse(String token,String refreshToken, String email, String tenantId, String deviceId) {

}
