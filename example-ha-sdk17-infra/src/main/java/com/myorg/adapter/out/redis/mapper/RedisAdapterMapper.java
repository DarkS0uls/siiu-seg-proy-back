package com.myorg.adapter.out.redis.mapper;

import com.myorg.adapter.out.redis.entity.RedisEntity;
import com.myorg.kernel.domain.out.redis.RedisDto;
import lombok.experimental.UtilityClass;

@UtilityClass
public class RedisAdapterMapper {

    public static RedisDto entityToDto(RedisEntity entity) {
        return RedisDto
                .builder()
                .id(entity.getId())
                .name(entity.getName())
                .email(entity.getEmail())
                .build();
    }

    public static RedisEntity dtoToEntity(RedisDto dto) {
        return RedisEntity
                .builder()
                .id(dto.getId())
                .name(dto.getName())
                .email(dto.getEmail())
                .build();
    }
}
