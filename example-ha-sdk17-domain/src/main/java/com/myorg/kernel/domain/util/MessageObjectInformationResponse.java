package com.myorg.kernel.domain.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Builder
@Getter
@AllArgsConstructor
public class MessageObjectInformationResponse implements Serializable {

    private String responseCode;
    private String responseMessage;
    private String responseDetail;
}
