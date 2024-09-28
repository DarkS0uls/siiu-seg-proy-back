package com.myorg.kernel.domain.util;

public enum StatusKeyEnum {
    ACTIVE,
    INACTIVE,
    LOCK,
    SUSPEND,
    CANCELED;

    public static StatusKeyEnum fromValue(String v) {
        return valueOf(v);
    }
    public String getName() {
        return name();
    }
}
