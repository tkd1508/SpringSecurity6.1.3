package com.io.securityInfrun.util.exception;

import org.springframework.security.core.AuthenticationException;

public class SecretException extends AuthenticationException {
    public SecretException(String message) {
        super(message);
    }
}
