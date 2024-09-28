package com.myorg.adapter.in.util;

public enum GenericDocumentTypes {
    CC,
    CE,
    NUIP,
    PPT,
    NIT,
    PEP,
    PA,
    OTRO;

    public static GenericDocumentTypes fromValue(String v) {
        return valueOf(v);
    }

    public String getName() {
        return name();
    }

}
