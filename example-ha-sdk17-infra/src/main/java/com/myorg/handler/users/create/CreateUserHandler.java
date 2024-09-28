package com.myorg.handler.users.create;

import com.myorg.adapter.in.error.mapper.ErrorResponseMapper;
import com.myorg.adapter.in.users.create.dto.CreateUserRequest;
import com.myorg.adapter.in.util.GenericResponse;
import com.myorg.handler.ResponseBuilder;
import com.myorg.handler.error.ErrorHandler;
import com.myorg.handler.users.create.mapper.CreateUserMapper;
import com.myorg.kernel.command.users.CreateUserCommand;
import com.myorg.usecase.users.create.CreateUserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class CreateUserHandler {

    private final CreateUserUseCase useCase;

    public Mono<ResponseEntity<GenericResponse>> execute(String messageUuid, String requestAppId, CreateUserRequest request) {
        return Mono.just(useCase.execute(buildCommand(messageUuid, requestAppId, request))
                        .fold(
                                ErrorResponseMapper::useCaseErrorToResponse,
                                CreateUserMapper::informationToResponse
                        ))
                .map(genericResponse -> new ResponseBuilder().mapResponseBuilder(genericResponse, HttpStatus.CREATED))
                .switchIfEmpty(Mono.just(ResponseEntity.noContent().build()))
                .onErrorResume(ErrorHandler::handlerError);
    }

    private CreateUserCommand buildCommand(String messageUuid, String requestAppId, CreateUserRequest request) {
        return CreateUserMapper.requestToCommand(messageUuid, requestAppId, request);
    }
}
