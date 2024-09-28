package com.myorg.kernel.domain.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class AbstractInformationMassiveResponse<T extends InformationPayload & Serializable> implements Serializable {

    private HeaderObjectInformationResponse headers;
    private MessageObjectInformationResponse messageResponse;
    private T data;
    private PaginationInformationPayLoad pagination;
}
