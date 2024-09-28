package com.myorg.kernel.domain.out.redis;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class RedisDto implements Serializable {
    private String id;
    private String name;
    private String email;
}
