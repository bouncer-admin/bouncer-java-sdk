package com.usebouncer.exception;

public class MissingApiKeyException extends BouncerException {
    public MissingApiKeyException(String message) {
        super(message);
    }
}
