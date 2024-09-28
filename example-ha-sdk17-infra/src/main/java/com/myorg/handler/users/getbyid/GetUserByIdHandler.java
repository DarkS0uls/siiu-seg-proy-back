package com.myorg.handler.users.getbyid;

import com.myorg.adapter.in.error.mapper.ErrorResponseMapper;
import com.myorg.adapter.in.util.GenericResponse;
import com.myorg.handler.ResponseBuilder;
import com.myorg.handler.error.ErrorHandler;
import com.myorg.handler.users.getbyid.mapper.GetUserByIdMapper;
import com.myorg.kernel.command.users.GetUSerByIdCommand;
import com.myorg.usecase.users.getbyid.GetUserByIdUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class GetUserByIdHandler {

    private final GetUserByIdUseCase useCase;

    public Mono<ResponseEntity<GenericResponse>> execute(String messageUuid, String requestAppId, String uuid) {
        return Mono.just(useCase.execute(buildCommand(messageUuid, requestAppId, uuid))
                        .fold(
                                ErrorResponseMapper::useCaseErrorToResponse,
                                GetUserByIdMapper::informationToResponse
                        ))
                .map(genericResponse -> new ResponseBuilder().mapResponseBuilder(genericResponse, HttpStatus.OK))
                .switchIfEmpty(Mono.just(ResponseEntity.noContent().build()))
                .onErrorResume(ErrorHandler::handlerError);
    }

    private GetUSerByIdCommand buildCommand(String messageUuid, String requestAppId, String uuid) {
        return GetUserByIdMapper.requestToCommand(messageUuid, requestAppId, uuid);
    }

}
