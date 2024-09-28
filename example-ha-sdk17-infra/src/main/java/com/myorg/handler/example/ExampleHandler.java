package com.myorg.handler.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.myorg.adapter.in.error.mapper.ErrorResponseMapper;
import com.myorg.adapter.in.util.GenericResponse;
import com.myorg.handler.ResponseBuilder;
import com.myorg.handler.error.ErrorHandler;
import com.myorg.handler.example.mapper.ExampleMapper;
import com.myorg.kernel.command.ExampleCommand;
import com.myorg.usecase.ExampleUseCase;
import io.swagger.annotations.Example;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Slf4j
public class ExampleHandler {

    private final ExampleUseCase useCase;

    public Mono<ResponseEntity<GenericResponse>> execute(String messageUuid, String requestAppId){
        return Mono.just(useCase.execute( buildCommand(messageUuid,requestAppId))
                        .fold(
                                        ErrorResponseMapper::useCaseErrorToResponse,
                                        ExampleMapper::informationToResponse
                                )
                )
                .map(genericResponse -> {

                    return new ResponseBuilder().mapResponseBuilder(genericResponse, HttpStatus.OK);

                })
                .switchIfEmpty(Mono.just(ResponseEntity.noContent().build()))
                .onErrorResume(ErrorHandler::handlerError);
    }
    private ExampleCommand buildCommand(String messageUuid, String requestAppId) {
        return ExampleMapper.requestToCommand(messageUuid,requestAppId);
    }

}
