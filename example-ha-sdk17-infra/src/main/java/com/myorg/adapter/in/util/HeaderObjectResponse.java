package com.myorg.adapter.in.util;

import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Builder
@Getter
public class HeaderObjectResponse implements Serializable {
    private String messageUuid;
    private String requestAppId;
    private String requestDatetime;
    private Integer httpStatusCode;
    private String httpStatusDesc;

}