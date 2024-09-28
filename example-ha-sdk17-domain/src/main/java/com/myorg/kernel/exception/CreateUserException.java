package com.myorg.kernel.exception;

public class CreateUserException extends RuntimeException {
    private final String errorMessage;
    private final Throwable errorDetail;
    public static final String UNKNOWN_DETAIL = "U";

    public CreateUserException(String message, Throwable detail) {
        this.errorMessage = message;
        this.errorDetail = detail;
    }
}