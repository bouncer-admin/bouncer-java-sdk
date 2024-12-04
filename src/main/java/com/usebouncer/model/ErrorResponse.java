package com.usebouncer.model;

import java.util.Map;
import java.util.Objects;

public class ErrorResponse {

    private int status;
    private String error;
    private String message;

    public ErrorResponse(int status, String error, String message) {
        this.status = status;
        this.error = error;
        this.message = message;
    }

    public static ErrorResponse parse(Map<String, Object> propertiesMap) {
        int status = (int) propertiesMap.get("status");
        String error = (String) propertiesMap.getOrDefault("error", null);
        String message = (String) propertiesMap.getOrDefault("message", null);

        return new ErrorResponse(status, error, message);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ErrorResponse that = (ErrorResponse) o;
        return status == that.status && Objects.equals(error, that.error) && Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, error, message);
    }
}
