package com.myorg.handler.niveles.getallniveles;

import com.myorg.adapter.in.error.mapper.ErrorResponseMapper;
import com.myorg.adapter.in.util.GenericResponse;
import com.myorg.handler.ResponseBuilder;
import com.myorg.handler.error.ErrorHandler;
import com.myorg.kernel.command.niveles.GetAllNivelesCommand;
import com.myorg.usecase.niveles.getallniveles.GetAllNivelesUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class GetAllNivelesHandler {

    private final GetAllNivelesUseCase getAllNivelesUseCase;

    public Mono<ResponseEntity<GenericResponse>> execute(String messageUuid,
                                                         String requestAppId,
                                                         Integer pageNumber,
                                                         Integer pageSize) {
        return Mono.just(getAllNivelesUseCase.execute(buildCommand(messageUuid, requestAppId, pageNumber, pageSize))
                        .fold(
                                ErrorResponseMapper::useCaseErrorToResponse,
                                GetAllNivelesMapper::informationToResponse
                        ))
                .map(genericResponse -> new ResponseBuilder().mapResponseBuilder(genericResponse, HttpStatus.OK))
                .switchIfEmpty(Mono.just(ResponseEntity.noContent().build()))
                .onErrorResume(ErrorHandler::handlerError);
    }

    private GetAllNivelesCommand buildCommand(String messageUuid, String requestAppId, Integer pageNumber, Integer pageSize) {
        return GetAllNivelesMapper.requestToCommand(messageUuid, requestAppId, pageNumber, pageSize);
    }
}
