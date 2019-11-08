package com.enigma.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class InsufficientFoodQuantityException extends RuntimeException {
    public InsufficientFoodQuantityException(String s) {
        super(s);
    }
    public InsufficientFoodQuantityException() {
        super("Food Quantity Is not Enough");
    }

    }
