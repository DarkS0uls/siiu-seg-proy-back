package com.myorg.adapter.in.util;

public enum GenericUserType {

    PN,
    PJ;

    public static GenericUserType fromValue(String v) {
        return valueOf(v);
    }

    public String getName() {
        return name();
    }

}
