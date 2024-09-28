package com.myorg.adapter.in.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GenericThirdPartyResponseData implements Serializable {

    private String id;
    private String entityCode;
    private String entityName;
    private String entityDocumentNumber;
    private String entityDocumentType;
    private Integer compensationTransitCode;
    private String status;
    private LocalDateTime createDt;
    private LocalDateTime updateDt;

}
