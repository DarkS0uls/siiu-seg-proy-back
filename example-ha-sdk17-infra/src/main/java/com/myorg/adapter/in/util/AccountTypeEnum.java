package com.myorg.adapter.in.util;

public enum AccountTypeEnum {
    SAVING_ACCOUNT,
    CURRENT_ACCOUNT,
    LOW_DEPOSIT,
    ORDINARY_DEPOSIT,
    INCLUSIVE_LOW_AMOUNT;

    public static AccountTypeEnum fromValue(String v) {
        return valueOf(v);
    }
    public String getName() {
        return name();
    }
}
