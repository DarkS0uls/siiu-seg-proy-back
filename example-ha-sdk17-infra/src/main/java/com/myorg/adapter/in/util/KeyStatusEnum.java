package com.myorg.adapter.in.util;

public enum KeyStatusEnum {
    ACTIVE,
    INACTIVE,
    LOCK,
    SUSPEND,
    CANCELED;

    public static KeyStatusEnum fromValue(String v) {
        return valueOf(v);
    }
    public String getName() {
        return name();
    }

}
