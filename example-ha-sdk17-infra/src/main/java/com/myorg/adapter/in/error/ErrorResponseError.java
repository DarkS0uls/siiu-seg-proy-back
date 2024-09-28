package com.myorg.adapter.in.error;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Builder
@Getter
@AllArgsConstructor
public class ErrorResponseError implements Serializable {

    private String errorCode;
    private String errorDetail;
}
