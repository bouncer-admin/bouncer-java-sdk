package com.usebouncer.exception;

public class BadRequestException extends BouncerException {
    public BadRequestException(String message) {
        super(message);
    }
}
