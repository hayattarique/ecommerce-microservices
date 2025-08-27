package com.nrt.tms.gateway.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nrt.tms.gateway.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
@Slf4j
public class AuthenticationFilter implements Ordered, GlobalFilter {
    private final JwtUtils jwtUtils;
    private static final List<String> openEndpoints = List.of("/user/api/v1/auth/register",
            "/user/api/v1/auth/login", "/user/api/v1/auth/refresh-token", "/user/api/v1/auth/update-password");


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String path = exchange.getRequest().getURI().getPath();
        if (isOpenApiEndpoint(path)) {
            return chain.filter(exchange);
        }
        //Get Headers from exchange
        String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return writeJson(exchange.getResponse(), "Authorization header missing or not starting with bearer", path);
        }
        final String token = authHeader.substring("Bearer ".length());
        if (!jwtUtils.validateToken(token)) {
            return writeJson(exchange.getResponse(), "Token is expired", path);
        }
        final String username = jwtUtils.extractUsername(token);
        final String tenantId = jwtUtils.extractClaims(token, "tenantId");
        final String deviceId = jwtUtils.extractClaims(token, "deviceId");
        log.info("request came for tenantId: {}, deviceId: {}, username:{}", tenantId, deviceId, username);
        exchange.getRequest().mutate().
                header("X-TenantID", tenantId).header("X-DeviceID", deviceId).header("username", username).build();
        return chain.filter(exchange);
    }


    private Mono<Void> writeJson(ServerHttpResponse response, String message, String path) {
        Map<String, Object> responseBody = Map.of("status", HttpStatus.UNAUTHORIZED, "error", message,
                "message", "Missing or invalid Authorization header", "path", path);
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        try {
            return response.writeWith(Mono.just(response.bufferFactory().wrap(new ObjectMapper().writeValueAsString(responseBody).getBytes())));
        } catch (JsonProcessingException e) {
            log.error("Error while processing JSON{}", e.getMessage());
            return response.setComplete();
        }
    }

    private boolean isOpenApiEndpoint(String path) {
        return openEndpoints.stream().anyMatch(path::startsWith);
    }

    @Override
    public int getOrder() {
        return -1;
    }
}
