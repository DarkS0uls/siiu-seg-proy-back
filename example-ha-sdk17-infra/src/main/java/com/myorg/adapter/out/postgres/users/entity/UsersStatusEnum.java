package com.myorg.adapter.out.postgres.users.entity;

public enum UsersStatusEnum {

    ACTIVE,
    INACTIVE;

    public static UsersStatusEnum fromString(String status) {
        for (UsersStatusEnum s : UsersStatusEnum.values()) {
            if (s.name().equalsIgnoreCase(status)) {
                return s;
            }
        }
        return null;
    }
}
