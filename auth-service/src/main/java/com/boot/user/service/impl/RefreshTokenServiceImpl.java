package com.boot.user.service.impl;

import com.boot.user.entities.RefreshToken;
import com.boot.user.entities.Users;
import com.boot.user.exception.AuthenticationException;
import com.boot.user.repositories.RefreshTokenRepository;
import com.boot.user.repositories.UserRepository;
import com.boot.user.service.RefreshTokenService;
import com.boot.user.utils.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class RefreshTokenServiceImpl implements RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;
    private static final long REFRESH_TOKEN_EXPIRATION_TIME = 30L * 24 * 60 * 60 * 1000; // 30 days in milliseconds

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public String generateRefreshToken(Long userId) {
        Users users = userRepository.findById(userId).orElseThrow(() -> new AuthenticationException(ErrorCode.USER_NOT_FOUND, "User not found with ID: " + userId));
        RefreshToken token = new RefreshToken();
        token.setToken(UUID.randomUUID().toString());
        token.setUser(users);
        token.setExpiredAt(LocalDateTime.now().plusNanos(REFRESH_TOKEN_EXPIRATION_TIME));
        token.setRevoked(false);
        refreshTokenRepository.save(token);
        return token.getToken();
    }

    @Override
    @Transactional(readOnly = true)
    public RefreshToken validateRefreshToken(String token) {
        RefreshToken refreshToken = refreshTokenRepository.findByToken(token)
                .orElseThrow(() -> new AuthenticationException(ErrorCode.INVALID_REFRESH_TOKEN, "Invalid refresh token: " + token));
        if (refreshToken.getExpiredAt().isAfter(LocalDateTime.now())) {
            throw new AuthenticationException(ErrorCode.REFRESH_TOKEN_EXPIRED, "Refresh token has expired: " + token);
        }
        if (refreshToken.isRevoked()) {
            throw new AuthenticationException(ErrorCode.REFRESH_TOKEN_REVOKED, "Refresh token has been revoked: " + token);
        }
        return refreshToken;
    }

    @Override
    @Transactional
    public boolean revokeRefreshToken(String token) {
        RefreshToken refreshToken = refreshTokenRepository.findByToken(token)
                .orElseThrow(() -> new AuthenticationException(ErrorCode.INVALID_REFRESH_TOKEN, "Invalid refresh token: " + token));
        refreshToken.setRevoked(true);
        refreshToken.setExpiredAt(LocalDateTime.now());
        refreshTokenRepository.save(refreshToken);
        return refreshToken.isRevoked();
    }

    @Override
    @Transactional
    public String rotateRefreshToken(RefreshToken oldToken) {
        oldToken.setRevoked(true);
        oldToken.setExpiredAt(LocalDateTime.now());
        refreshTokenRepository.save(oldToken);
        RefreshToken newToken = new RefreshToken();
        newToken.setUser(oldToken.getUser());
        newToken.setToken(UUID.randomUUID().toString());
        newToken.setExpiredAt(LocalDateTime.now().plusNanos(REFRESH_TOKEN_EXPIRATION_TIME));
        newToken.setRevoked(false);
        refreshTokenRepository.save(newToken);
        return newToken.getToken();
    }


}
