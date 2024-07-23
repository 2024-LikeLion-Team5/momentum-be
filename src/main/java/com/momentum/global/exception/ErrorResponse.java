package com.momentum.global.exception;

public record ErrorResponse(
        String fieldName,
        String message
) {

    @Override
    public String toString() {
        return "{" +
                "fieldName='" + fieldName + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
