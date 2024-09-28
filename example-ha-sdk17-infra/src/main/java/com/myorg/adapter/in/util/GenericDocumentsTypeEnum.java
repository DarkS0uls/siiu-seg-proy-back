package com.myorg.adapter.in.util;

public enum GenericDocumentsTypeEnum {
    CC,
    CE,
    NT,
    PA,
    CX,
    TI;

    public static GenericDocumentsTypeEnum fromValue(String v) {
        return valueOf(v);
    }

    public String getName() {
        return name();
    }

}
