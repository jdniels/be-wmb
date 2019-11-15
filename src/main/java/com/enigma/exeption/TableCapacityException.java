package com.enigma.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class TableCapacityException extends RuntimeException {
    public TableCapacityException(String s) {
        super(s);
    }
    public TableCapacityException() {
        super("Not Enough Table Capacity");
    }
}
