package com.enigma.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class NotEnoughtMoneyException extends RuntimeException {
    public NotEnoughtMoneyException(String s) {
        super(s);
    }

    public NotEnoughtMoneyException() {
        super("Money is not Enough !");
    }
}
