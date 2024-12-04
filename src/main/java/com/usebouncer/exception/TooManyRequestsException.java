package com.usebouncer.exception;

public class TooManyRequestsException extends BouncerException {
    public TooManyRequestsException(String message) {
        super(message);
    }
}
