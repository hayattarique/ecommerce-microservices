package com.boot.user.controller;

import com.boot.user.annotation.Audit;
import com.boot.user.dto.*;
import com.boot.user.entities.RefreshToken;
import com.boot.user.entities.Users;
import com.boot.user.service.RefreshTokenService;
import com.boot.user.service.UserService;
import com.boot.user.utils.ApiCode;
import com.boot.user.utils.ApiResponseBuilder;
import com.boot.user.utils.AuditEventType;
import com.boot.user.utils.MappingConstant;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(MappingConstant.AuthMapping.BASE)
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final RefreshTokenService refreshTokenService;
    private final ApiResponseBuilder response;

    @PostMapping(MappingConstant.AuthMapping.REGISTER)
    @Audit(action = AuditEventType.USER_REGISTER, description = "User registration")
    public ResponseEntity<ApiResponse<Boolean>> createUser(@RequestBody @Valid SignupRequest userDto) {
        return response.ok(userService.userRegister(userDto), ApiCode.CREATED.getCode(), ApiCode.CREATED.getMessage());
    }

    @PutMapping(MappingConstant.AuthMapping.UPDATE_PASSWORD)
    @Audit(action = AuditEventType.PASSWORD_CHANGE, description = "Set or change user password")
    public ResponseEntity<String> setPassword(@RequestBody UpdateCredentialDto credentialDto) {
        return ResponseEntity.ok(userService.changePassword(credentialDto));
    }

    @PostMapping(MappingConstant.AuthMapping.REFRESH)
    public ResponseEntity<AuthResponse> refreshToken(@RequestBody TokenRefreshRequest request) {
        RefreshToken refreshToken = refreshTokenService.validateRefreshToken(request.refreshToken());
        String rotatedRefreshToken = refreshTokenService.rotateRefreshToken(refreshToken);
        Users user = refreshToken.getUser();
        String accessToken = userService.generateJwtToken(user, request.deviceId());
        return ResponseEntity.ok(new AuthResponse(accessToken, rotatedRefreshToken, user.getEmail(), user.getTenantId(), request.deviceId()));

    }

    @PostMapping(MappingConstant.AuthMapping.LOGIN)
    @Audit(action = AuditEventType.LOGIN, description = "User login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(userService.login(request));
    }

    @PutMapping(MappingConstant.AuthMapping.UPDATE_USER)
    public ResponseEntity<String> updateUser(@RequestBody UpdateRequest request) {
        return ResponseEntity.ok(userService.changeAccountStatus(request.getEmail(), request.getStatus()));
    }

}