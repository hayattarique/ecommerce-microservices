package org.spring.hayat.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorResponse {
    private String code;
    private String message;
    private String path;
    private LocalDateTime timestamp; // when error happened
    private String service;    // which microservice raised it (product-service, order-service, etc.)
}
