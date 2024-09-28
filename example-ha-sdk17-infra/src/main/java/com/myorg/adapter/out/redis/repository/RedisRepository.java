package com.myorg.adapter.out.redis.repository;

import com.myorg.adapter.out.redis.entity.RedisEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RedisRepository extends CrudRepository<RedisEntity, String> {
}
