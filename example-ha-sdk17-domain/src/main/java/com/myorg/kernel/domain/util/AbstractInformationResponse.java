package com.myorg.kernel.domain.util;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class AbstractInformationResponse<T extends InformationPayload & Serializable> implements Serializable {

    private HeaderObjectInformationResponse headers;
    private MessageObjectInformationResponse messageResponse;
    private T data;
}
