package com.myorg.kernel.exception;

import lombok.Getter;

@Getter
public class ThirdPartyException extends RuntimeException {
    private final String errorMessage;
    private final Throwable errorDetail;
    public static final String UNKNOWN_DETAIL = "U";

    public ThirdPartyException(String message, Throwable detail) {
        this.errorMessage = message;
        this.errorDetail = detail;
    }

}