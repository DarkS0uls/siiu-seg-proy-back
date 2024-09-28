package com.myorg.ports;

import com.myorg.kernel.domain.out.redis.RedisDto;
import reactor.core.publisher.Mono;

public interface RedisPort {
    Mono<RedisDto> save(RedisDto redisDto);
    Mono<RedisDto> findById(String id);

}
