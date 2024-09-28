package com.myorg.adapter.in.util;

import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Builder
@Getter
public class MessageObjectResponse implements Serializable {
    private String responseCode;
    private String responseMessage;
    private String responseDetails;
}