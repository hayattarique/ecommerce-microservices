package com.nrt.tms.gateway.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class JwtUtils {
    private final JWTVerifier jwtVerifier;

    /**
     * Constructor to initialize JwtUtils with the provided secret key. and algorithm is HMAC256 used for signing the JWTs.
     *
     * @param secret the secret key used for signing and verifying JWTs
     */
    public JwtUtils(@Value("${jwt.app.jwt-secret}") String secret) {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        this.jwtVerifier = JWT.require(algorithm).build();
    }

    public String extractClaims(String token, String claim) {
        return jwtVerifier.verify(token).getClaim(claim).asString();
    }

    public boolean validateToken(String token) {
        boolean flag = false;
        try {
            jwtVerifier.verify(token);
            flag = true;
        } catch (JWTVerificationException e) {
            log.error("Token validation error: {}", e.getMessage());
        }
        return flag;
    }


    public String extractUsername(String token) {
        return jwtVerifier.verify(token).getSubject();
    }
}
