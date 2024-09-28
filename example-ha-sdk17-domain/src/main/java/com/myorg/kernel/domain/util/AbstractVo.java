package com.myorg.kernel.domain.util;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Optional;

@Getter
@NoArgsConstructor
public class AbstractVo<T extends Serializable> implements Serializable {
    private Boolean success;
    private String responseMessage;
    private T payload;

    public AbstractVo(Boolean success, String message, T payload) {
        this.success = success;
        this.responseMessage = message;
        this.payload = payload;
    }


}

