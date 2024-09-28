package com.myorg.service.uuid;

import java.util.UUID;
public class UuidManagerService {

    private UUID uuid;

    public UuidManagerService() {
        uuid = UUID.randomUUID();
    }
    public UUID  getUuid() {
        uuid = UUID.randomUUID();
        return uuid;
    }
    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }
    @Override
    public String toString() {
        return "GenerateUUID [uuid=" + uuid + "]";
    }
}