package com.enigma.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class StatusTableException extends RuntimeException {
    public StatusTableException(String s) {
        super(s);
    }

    public StatusTableException() {
        super("the status does not allow to be changed");
    }
}
