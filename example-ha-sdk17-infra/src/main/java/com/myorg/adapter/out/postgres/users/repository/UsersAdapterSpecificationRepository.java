package com.myorg.adapter.out.postgres.users.repository;

import com.myorg.adapter.out.postgres.users.entity.UsersEntity;
import io.swagger.models.auth.In;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersAdapterSpecificationRepository extends CrudRepository<UsersEntity, Integer> , JpaSpecificationExecutor<UsersEntity> {
}
