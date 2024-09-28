package com.myorg.adapter.in.util;

public enum KeyTypeEnum {
    ID,
    CEL,
    EMAIL,
    MERCHANTID,
    ALFANUMERIC;

    public static KeyTypeEnum fromValue(String v) {
        return valueOf(v);
    }
    public String getName() {
        return name();
    }
}
