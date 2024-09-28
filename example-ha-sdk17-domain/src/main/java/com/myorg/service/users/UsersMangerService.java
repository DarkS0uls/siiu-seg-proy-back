package com.myorg.service.users;

import com.myorg.kernel.domain.out.postgres.users.UsersDto;
import com.myorg.kernel.domain.out.postgres.users.UsersMassiveDto;
import com.myorg.kernel.domain.util.AbstractVo;
import com.myorg.ports.UsersPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Slf4j
public class UsersMangerService {

    private final UsersPort usersPort;

    public Mono<AbstractVo<UsersDto>> createUser(UsersDto usersDto) {
        log.info("UsersMangerService.createUser,Creating user with name: {}, and email: {}", usersDto.getName(), usersDto.getEmail());
        return usersPort.createUser(usersDto)
                .doOnSuccess(m -> log.info("UsersMangerService.createUser,User created successfully, with id: {}, user name:{} and email:{}", m.getId(), m.getName(), m.getEmail()))
                .map(responseDto -> new AbstractVo<>(true, "User created successfully", responseDto))
                .doOnError(e -> log.error("UsersMangerService.createUser,Error creating user, with user name: {},email:{} and details:{}", usersDto.getName(), usersDto.getEmail(), e.getMessage()))
                .onErrorResume(e -> Mono.just(new AbstractVo<>(false, "Error creating user", null)));
    }

    public Mono<AbstractVo<UsersDto>> getUserByUuid(String uuid) {
        log.info("UsersMangerService.getUserByUuid,Getting user by uuid: {}", uuid);
        return usersPort.getUserByUuid(uuid)
                .doOnSuccess(m -> log.info("UsersMangerService.getUserByUuid,User found with id: {}, user name:{} and email:{}", m.getId(), m.getName(), m.getEmail()))
                .map(responseDto -> new AbstractVo<>(true, "User found successfully", responseDto))
                .doOnError(e -> log.error("UsersMangerService.getUserByUuid,Error getting user by uuid: {}, details:{}", uuid, e.getMessage()))
                .onErrorResume(e -> Mono.just(new AbstractVo<>(false, "Error getting user by uuid", null)));
    }

    public Mono<AbstractVo<UsersMassiveDto>> getAllUsers(Integer pageNumber,
                                                         Integer pageSize,
                                                         String status,   //query by enum value
                                                         String userName, //query by like value
                                                         String cellphone, //query by exactly value
                                                         LocalDateTime createdDt //query by date
                                                         ){
        log.info("UsersMangerService.getAllUsers,Getting all users with pageNumber: {}, pageSize: {}, status: {}, userName: {}, cellphone: {}", pageNumber, pageSize, status, userName, cellphone);
        return usersPort.getAllUsers(pageNumber, pageSize, status, userName, cellphone, createdDt)
                .doOnSuccess(m -> log.info("UsersMangerService.getAllUsers,Users found successfully, with total users: {}", m.getUsers().size()))
                .map(responseDto -> new AbstractVo<>(true, "Users found successfully", responseDto))
                .doOnError(e -> log.error("UsersMangerService.getAllUsers,Error getting users, details:{}", e.getMessage()))
                .onErrorResume(e -> Mono.just(new AbstractVo<>(false, "Error getting users", null)));
        }


    }
