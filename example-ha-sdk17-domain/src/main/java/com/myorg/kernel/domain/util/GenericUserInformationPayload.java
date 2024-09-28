package com.myorg.kernel.domain.util;

import com.myorg.kernel.domain.util.InformationPayload;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class GenericUserInformationPayload implements Serializable, InformationPayload {

    private String uuid;
    private String userName;
    private String secondName;
    private String lastName;
    private String email;
    private String cellphone;
    private String status;
    private String createDt;
    private String updateDt;

}
