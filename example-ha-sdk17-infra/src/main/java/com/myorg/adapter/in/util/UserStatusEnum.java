package com.myorg.adapter.in.util;

public enum UserStatusEnum {
    ACTIVE, INACTIVE;

    public static UserStatusEnum fromValue(String v) {
        return valueOf(v);
    }
    public String getName() {
        return name();
    }
}
