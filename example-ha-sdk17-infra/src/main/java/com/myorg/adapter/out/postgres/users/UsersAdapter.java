package com.myorg.adapter.out.postgres.users;

import com.myorg.adapter.out.postgres.users.mapper.UsersMapper;
import com.myorg.adapter.out.postgres.users.repository.UsersAdapterSpecificationRepository;
import com.myorg.adapter.out.postgres.users.repository.UsersRepository;
import com.myorg.kernel.domain.out.postgres.users.UsersDto;
import com.myorg.kernel.domain.out.postgres.users.UsersMassiveDto;
import com.myorg.kernel.exception.CreateUserException;
import com.myorg.ports.UsersPort;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
@Component
public class UsersAdapter implements UsersPort {

    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private UsersAdapterSpecificationRepository usersAdapterSpecificationRepository;


    @Override
    public Mono<UsersDto> createUser(UsersDto usersDto) {
        log.info("UsersAdapter.createUser, with user name: {}, and email: {}", usersDto.getName(), usersDto.getEmail());

        return Mono.just(usersRepository.save(UsersMapper.dtoToEntity(usersDto)))
                .map(UsersMapper::entityToDto)
                .doOnSuccess(m -> log.info("UsersAdapter.createUser,User created successfully, with id: {}, user name:{} and email:{}", m.getId(), m.getName(), m.getEmail()))
                .doOnError(e -> log.error("UsersAdapter.createUser,Error creating user, with user name: {},email:{} and details:{}", usersDto.getName(), usersDto.getEmail(), e.getMessage()))
                .onErrorResume(DataAccessException.class, e -> Mono.error(new CreateUserException(String.format("Error creating user, with username:%s, email:%s, details:%s", usersDto.getName(), usersDto.getEmail(), e.getMessage()), e.getCause())))
                .onErrorResume(SQLException.class, e1 -> Mono.error(new SQLException(String.format("Error creating user, with username:%s, email:%s, details:%s", usersDto.getName(), usersDto.getEmail(), e1.getMessage()), e1.getCause())))
                .onErrorResume(e -> Mono.error(new CreateUserException(String.format("Error creating user, with username:%s, email:%s, details:%s", usersDto.getName(), usersDto.getEmail(), e.getMessage()), e.getCause())));

    }

    @Override
    public Mono<UsersDto> updateUser(UsersDto usersDto) {
        return Mono.just(usersRepository.save(UsersMapper.dtoToEntity(usersDto)))
                .map(UsersMapper::entityToDto)
                .doOnSuccess(m -> log.info("UsersAdapter.updateUser,User updated successfully, with id: {}, user name:{} and email:{}", m.getId(), m.getName(), m.getEmail()))
                .doOnError(e -> log.error("UsersAdapter.updateUser,Error updating user, with user name: {},email:{} and details:{}", usersDto.getName(), usersDto.getEmail(), e.getMessage()))
                .onErrorResume(DataAccessException.class, e -> Mono.error(new CreateUserException(String.format("Error creating user, with username:%s, email:%s, details:%s", usersDto.getName(), usersDto.getEmail(), e.getMessage()), e.getCause())))
                .onErrorResume(SQLException.class, e1 -> Mono.error(new SQLException(String.format("Error updating user, with username:%s, email:%s, details:%s", usersDto.getName(), usersDto.getEmail(), e1.getMessage()), e1.getCause())))
                .onErrorResume(e -> Mono.error(new CreateUserException(String.format("Error updating user, with username:%s, email:%s, details:%s", usersDto.getName(), usersDto.getEmail(), e.getMessage()), e.getCause())));

    }

    @Override
    public Mono<Void> deleteUser(UsersDto usersDto) {
        return Mono.fromRunnable(() -> usersRepository.delete(UsersMapper.dtoToEntity(usersDto)))
                .doOnSuccess(m -> log.info("UsersAdapter.deleteUser,User deleted successfully, with id: {}, user name:{} and email:{}", usersDto.getId(), usersDto.getName(), usersDto.getEmail()))
                .doOnError(e -> log.error("UsersAdapter.deleteUser,Error deleting user, with user name: {},email:{} and details:{}", usersDto.getName(), usersDto.getEmail(), e.getMessage()))
                .onErrorResume(DataAccessException.class, e -> Mono.error(new CreateUserException(String.format("Error deleting user, with username:%s, email:%s, details:%s", usersDto.getName(), usersDto.getEmail(), e.getMessage()), e.getCause())))
                .onErrorResume(SQLException.class, e -> Mono.error(new SQLException(String.format("Error deleting user, with username:%s, email:%s, details:%s", usersDto.getName(), usersDto.getEmail(), e.getMessage()), e.getCause())))
                .onErrorResume(e -> Mono.error(new CreateUserException(String.format("Error deleting user, with username:%s, email:%s, details:%s", usersDto.getName(), usersDto.getEmail(), e.getMessage()), e.getCause())))
                .then();
    }

    @Override
    public Mono<UsersDto> getUserById(Integer id) {
        log.info("UsersAdapter.getUserById, with id: {}", id);
        return Mono.just(usersRepository.findById(id))
                .map(m -> m.isPresent() ? UsersMapper.entityToDto(m.get()) : new UsersDto())
                .doOnSuccess(m -> log.info("UsersAdapter.getUserById,User found successfully, with id: {}, user name:{} and email:{}", m.getId(), m.getName(), m.getEmail()))
                .doOnError(e -> log.error("UsersAdapter.getUserById,Error finding user, with id: {}, details:{}", id, e.getMessage()))
                .onErrorResume(DataAccessException.class, e -> Mono.error(new CreateUserException(String.format("Error finding user, with id:%s, details:%s", id, e.getMessage()), e.getCause())))
                .onErrorResume(SQLException.class, e1 -> Mono.error(new SQLException(String.format("Error finding user, with id:%s, details:%s", id, e1.getMessage()), e1.getCause())))
                .onErrorResume(e -> Mono.error(new CreateUserException(String.format("Error finding user, with id:%s, details:%s", id, e.getMessage()), e.getCause())));
    }

    @Override
    public Mono<UsersDto> getUserByUuid(String uuid) {
        log.info("UsersAdapter.getUserByUuid, with uuid: {}", uuid);
        return Mono.just(usersRepository.findByUuid(UUID.fromString(uuid)))
                .map(m -> m.isPresent() ? UsersMapper.entityToDto(m.get()) : new UsersDto())
                .doOnSuccess(m -> log.info("UsersAdapter.findByUuid,User found successfully, with uuid: {}, user name:{} and email:{}", m.getId(), m.getName(), m.getEmail()))
                .doOnError(e -> log.error("UsersAdapter.findByUuid,Error finding user, with uuid: {}, details:{}", uuid, e.getMessage()))
                .onErrorResume(DataAccessException.class, e -> Mono.error(new CreateUserException(String.format("Error finding user, with uuid:%s, details:%s", uuid, e.getMessage()), e.getCause())))
                .onErrorResume(SQLException.class, e1 -> Mono.error(new SQLException(String.format("Error finding user, with uuid:%s, details:%s", uuid, e1.getMessage()), e1.getCause())))
                .onErrorResume(e -> Mono.error(new CreateUserException(String.format("Error finding user, with uuid:%s, details:%s", uuid, e.getMessage()), e.getCause())));
    }

    @Override
    public Mono<UsersMassiveDto> getAllUsers(Integer pageNumber, Integer pageSize, String status, String userName, String cellphone, LocalDateTime createdDt) {
        log.info("UsersAdapter.getAllUsers, with page number: {}, and page size: {}", pageNumber, pageSize);
        return Mono.just(usersAdapterSpecificationRepository.findAll(buildUserSpecification(userName, status, cellphone, createdDt), PageRequest.of(pageNumber-1, pageSize)))
                .map(page->{
                    log.info("page: {}", page.toList().size());
                    return page;
                })
                .map(m -> UsersMapper.entityToMassiveDto(m, pageSize, pageNumber))
                .doOnSuccess(m -> log.info("UsersAdapter.getAllUsers,Users found successfully, with total users: {}", m.getUsers().size()))
                .doOnError(e -> log.error("UsersAdapter.getAllUsers,Error finding users, with details:{}", e.getMessage()))
                .onErrorResume(DataAccessException.class, e -> Mono.error(new CreateUserException(String.format("Error finding users, details:%s", e.getMessage()), e.getCause())))
                .onErrorResume(SQLException.class, e1 -> Mono.error(new SQLException(String.format("Error finding users, details:%s", e1.getMessage()), e1.getCause())))
                .onErrorResume(e -> Mono.error(new CreateUserException(String.format("Error finding users, details:%s", e.getMessage()), e.getCause())));
    }

    private UsersAdapterSpecification buildUserSpecification(String status, String userName, String cellphone, LocalDateTime createdDt) {
        log.info("UsersAdapter.buildUserSpecification, with status: {}, userName: {}, cellphone: {}", status, userName, cellphone);
        return UsersAdapterSpecification
                .builder()
                .userName(userName)
                .status(status)
                .cellphone(cellphone)
                .createdDt(createdDt)
                .build();
    }

}
