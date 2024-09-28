package com.myorg.ports;

import com.myorg.kernel.domain.out.postgres.users.UsersDto;
import com.myorg.kernel.domain.out.postgres.users.UsersMassiveDto;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

public interface UsersPort {

    Mono<UsersDto> createUser(UsersDto usersDto);
    //Transaccion1 --> FluentD
    //Collector-->Dynamo, **Postgres**, s3

    Mono<UsersDto> updateUser(UsersDto usersDto);

    Mono<Void> deleteUser(UsersDto usersDto);

    Mono<UsersDto> getUserById(Integer id);

    Mono<UsersDto> getUserByUuid(String uuid);

    Mono<UsersMassiveDto> getAllUsers(Integer pageNumber,
                                      Integer pageSize,
                                      String status,    //query by enum value
                                      String userName,  //query by like value
                                      String cellphone, //query by exactly value
                                      LocalDateTime createdDt  //query by date
    );
}
