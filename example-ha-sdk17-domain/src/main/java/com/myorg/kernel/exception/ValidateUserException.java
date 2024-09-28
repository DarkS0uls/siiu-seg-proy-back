package com.myorg.kernel.exception;

import lombok.Getter;

@Getter
public class ValidateUserException extends RuntimeException {
    private final String errorMessage;
    private final Throwable errorDetail;
    public static final String UNKNOWN_DETAIL = "U";

    public ValidateUserException(String message, Throwable detail) {
        this.errorMessage = message;
        this.errorDetail = detail;
    }

}
