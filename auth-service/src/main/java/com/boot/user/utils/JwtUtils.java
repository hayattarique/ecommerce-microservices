package com.boot.user.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {
    private final Algorithm algorithm;
    private final JWTVerifier jwtVerifier;
    private final long expirationTime;


    public JwtUtils(@Value("${jwt.app.jwt-secret}") String secretKey, @Value("${jwt.app.expiration}") long expirationTime) {
        this.algorithm = Algorithm.HMAC256(secretKey);
        this.jwtVerifier = JWT.require(algorithm).build();
        this.expirationTime = expirationTime;

    }

    public String generateToken(UserDetails userDetails, String tenantId, String deviceId) {
        return JWT.create().withClaim("tenantId", tenantId).withClaim("deviceId", deviceId)
                .withSubject(userDetails.getUsername())
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + expirationTime)).sign(algorithm);
    }

    public String extractUsername(String token) {
        return validateToken(token).getSubject();
    }

    public String extractClaim(String token, String claimName) {
        return validateToken(token).getClaim(claimName).asString();
    }

    public boolean isTokenValid(String token, UserDetails user) {
        return extractUsername(token).equals(user.getUsername());
    }

    private DecodedJWT validateToken(String token) {
        return jwtVerifier.verify(token);
    }
}
