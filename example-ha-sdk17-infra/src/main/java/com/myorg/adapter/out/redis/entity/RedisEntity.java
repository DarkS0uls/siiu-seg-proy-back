package com.myorg.adapter.out.redis.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@RedisHash("redis")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class RedisEntity implements Serializable {

    @Id
    private String id;
    private String name;
    private String email;

}
