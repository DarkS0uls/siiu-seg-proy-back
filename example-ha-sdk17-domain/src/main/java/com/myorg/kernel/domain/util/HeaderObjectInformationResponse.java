package com.myorg.kernel.domain.util;

import com.myorg.service.time.TimeManagerService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Builder
@Getter
@AllArgsConstructor
public class HeaderObjectInformationResponse implements Serializable {
    private Integer httpStatusCode;
    private String httpStatusDesc;
    private String messageUuid;
    private String requestDatetime;
    private String requestAppId;


    public String getRequestDatetime() {
        return requestDatetime == null ? (new TimeManagerService().getInstantIsoFormat()) : requestDatetime;
    }

}
