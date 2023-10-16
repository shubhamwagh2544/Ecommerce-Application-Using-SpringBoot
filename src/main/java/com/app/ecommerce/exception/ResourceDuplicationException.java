package com.app.ecommerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class ResourceDuplicationException extends RuntimeException {
    public ResourceDuplicationException(String message) {
        super(message);
    }
}
