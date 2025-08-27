package org.spring.hayat.exception;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {
    private final String code;
    private final String message;
    private final String serviceName;

    public BusinessException(String code, String message, String serviceName) {
        super(message);
        this.message = message;
        this.code = code;
        this.serviceName = serviceName;
    }
}
