package com.enigma.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class NotEnoughtMoneyException extends RuntimeException {
    public NotEnoughtMoneyException() {
        super("Not Enought Money");
    }
}
