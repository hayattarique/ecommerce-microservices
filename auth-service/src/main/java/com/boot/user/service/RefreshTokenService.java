package com.boot.user.service;

import com.boot.user.entities.RefreshToken;

public interface RefreshTokenService {
    /**
     * Generates a new refresh token for the user.
     *
     * @param userId the ID of the user for whom the refresh token is generated
     * @return the generated refresh token
     */
    String generateRefreshToken(Long userId);

    /**
     * Validates the provided refresh token.
     *
     * @param token the refresh token to validate
     * @return returns token if valid or an error message if invalid
     */
    RefreshToken validateRefreshToken(String token);

    /**
     * Revokes the specified refresh token.
     *
     * @param token the refresh token to revoke
     * @return true if the token was successfully revoked, false otherwise
     */
    boolean revokeRefreshToken(String token);


    String rotateRefreshToken(RefreshToken refreshToken);
}
