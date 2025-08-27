package com.boot.user.exception;

import com.boot.user.utils.ErrorCode;
import lombok.Getter;

@Getter
public class AuthenticationException extends RuntimeException {
    private final ErrorCode errorCode;
    private final String message;

    public AuthenticationException(ErrorCode errorCode, String message) {
        super(message);
        this.message = message;
        this.errorCode = errorCode;
    }
}
