package com.myorg.adapter.out.redis;

import com.myorg.adapter.out.redis.mapper.RedisAdapterMapper;
import com.myorg.adapter.out.redis.repository.RedisRepository;
import com.myorg.kernel.domain.out.redis.RedisDto;
import com.myorg.ports.RedisPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Slf4j
public class RedisAdapter implements RedisPort
{
    @Autowired
    private RedisRepository redisRepository;

    @Override
    public Mono<RedisDto> save(RedisDto redisDto)
    {
        return Mono.just(redisRepository.save(RedisAdapterMapper.dtoToEntity(redisDto)))
                .map(RedisAdapterMapper::entityToDto)
                .doOnSuccess(dto -> log.info("RedisDto saved: {}", dto))
                .doOnError(e -> log.error("Error saving RedisDto", e))
                .onErrorResume(e -> Mono.error(new RuntimeException("Error saving RedisDto", e)));
    }

    @Override
    public Mono<RedisDto> findById(String id) {
       return Mono.just(redisRepository.findById(id))
               .map(m->m.isPresent()? RedisAdapterMapper.entityToDto(m.get()):null)
               .switchIfEmpty(Mono.error(new RuntimeException("RedisDto not found")))
               .doOnSuccess(dto -> log.info("RedisDto found: {}", dto))
               .doOnError(e -> log.error("Error finding RedisDto", e))
               .onErrorResume(e -> Mono.error(new RuntimeException("Error finding RedisDto", e)));
    }

}
