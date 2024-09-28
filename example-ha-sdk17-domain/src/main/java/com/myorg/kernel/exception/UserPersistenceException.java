package com.myorg.kernel.exception;

import lombok.Getter;

@Getter
public class UserPersistenceException extends RuntimeException {
    private final String errorMessage;
    private final Throwable errorDetail;
    public static final String UNKNOWN_DETAIL = "U";

    public UserPersistenceException(String message, Throwable detail) {
        this.errorMessage = message;
        this.errorDetail = detail;
    }


}
