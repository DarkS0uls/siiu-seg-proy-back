package com.myorg.adapter.in.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class GenericUserResponseData implements Serializable {

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
