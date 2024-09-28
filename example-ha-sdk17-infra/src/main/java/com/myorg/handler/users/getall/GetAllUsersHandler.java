package com.myorg.handler.users.getall;

import com.myorg.adapter.in.error.mapper.ErrorResponseMapper;
import com.myorg.adapter.in.util.GenericResponse;
import com.myorg.handler.ResponseBuilder;
import com.myorg.handler.error.ErrorHandler;
import com.myorg.handler.users.getall.mapper.GetAllUsersMapper;
import com.myorg.handler.users.getbyid.mapper.GetUserByIdMapper;
import com.myorg.kernel.command.users.GetAllUsersCommand;
import com.myorg.kernel.command.users.GetUSerByIdCommand;
import com.myorg.usecase.users.getall.GetAllUsersUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class GetAllUsersHandler {

    private final GetAllUsersUseCase useCase;

    public Mono<ResponseEntity<GenericResponse>> execute(String messageUuid,
                                                         String requestAppId,
                                                         Integer pageNumber,
                                                         Integer pageSize,
                                                         String status,
                                                         String userName,
                                                         String cellphone,
                                                         String createdDt) {

        return Mono.just(useCase.execute(buildCommand(messageUuid, requestAppId, pageNumber, pageSize, status, userName, cellphone, createdDt))
                        .fold(
                                ErrorResponseMapper::useCaseErrorToResponse,
                                GetAllUsersMapper::informationToResponse
                        ))
                .map(genericResponse -> new ResponseBuilder().mapResponseBuilder(genericResponse, HttpStatus.OK))
                .switchIfEmpty(Mono.just(ResponseEntity.noContent().build()))
                .onErrorResume(ErrorHandler::handlerError);
    }

    private GetAllUsersCommand buildCommand(String messageUuid, String requestAppId, Integer pageNumber, Integer pageSize, String status, String userName, String cellphone, String createdDt) {
        return GetAllUsersMapper.requestToCommand(messageUuid, requestAppId, pageNumber, pageSize, status, userName, cellphone, createdDt);
    }

}
