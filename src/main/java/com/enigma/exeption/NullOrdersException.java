package com.enigma.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class NullOrdersException extends RuntimeException {
    public NullOrdersException() {
        super("Order At Least One Menu");
    }

    public NullOrdersException(String s) {
        super(s);
    }
}
