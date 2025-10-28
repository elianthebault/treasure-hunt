package com.treasure_hunt.application.exception;

public class DomainException extends RuntimeException {
    public DomainException(String message) {
        super(message);
    }
}
