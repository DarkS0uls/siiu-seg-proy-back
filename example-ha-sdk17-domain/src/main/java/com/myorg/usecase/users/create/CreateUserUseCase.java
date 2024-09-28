package com.myorg.usecase.users.create;

import com.myorg.kernel.command.users.CreateUserCommand;
import com.myorg.kernel.domain.in.users.create.CreateUserInformation;
import com.myorg.kernel.domain.out.postgres.users.UsersDto;
import com.myorg.kernel.domain.util.GenericResponseCodes;
import com.myorg.kernel.domain.util.HttpStatus;
import com.myorg.kernel.exception.error.UseCaseErrorResponse;
import com.myorg.kernel.exception.error.mapper.UseCaseErrorResponseMapper;
import com.myorg.service.time.TimeManagerService;
import com.myorg.service.users.UsersMangerService;
import com.myorg.service.uuid.UuidManagerService;
import com.myorg.usecase.UseCase;
import com.myorg.usecase.users.create.mapper.CreateUserUseCaseMapper;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Slf4j
public class CreateUserUseCase implements UseCase<CreateUserCommand, Either<UseCaseErrorResponse, CreateUserInformation>> {

    private final UsersMangerService usersMangerService;
    private final TimeManagerService timeManagerService;
    private final UuidManagerService uuidManagerService;

    @Override
    public Either<UseCaseErrorResponse, CreateUserInformation> execute(CreateUserCommand command) {
        log.info("CreateUserUseCase.execute,Creating user with name: {}, and lastName: {}", command.getUserName(), command.getLastName());
        return usersMangerService
                .createUser(CreateUserUseCaseMapper.commandToDto(command, uuidManagerService.getUuid(), timeManagerService.getLocalDateTime()))
                .map(userVo -> {
                    if (userVo.getSuccess()) {
                        return buildSuccessResponse(
                                userVo.getPayload(),
                                command.getMessageUuid(),
                                command.getRequestAppId(),
                                String.format("User Created with UUID: %s", userVo.getPayload().getUuid()));
                    } else {
                        return buildErrorResponse(
                                command,
                                userVo.getResponseMessage(),
                                HttpStatus.BAD_GATEWAY,
                                GenericResponseCodes.TRANSACCION_FALLIDA,
                                "Error Creating User");
                    }
                })
                .onErrorResume(e -> {
                    log.error("CreateUserUseCase.execute,Error creating user, with user name: {},lastName:{} and details:{}", command.getUserName(), command.getLastName(), e.getMessage());
                    return Mono.just(buildErrorResponse(
                            command,
                            "Error creating user",
                            HttpStatus.INTERNAL_SERVER_ERROR,
                            GenericResponseCodes.TRANSACCION_FALLIDA,
                            ""));
                })
                .block();

    }

    private Either<UseCaseErrorResponse, CreateUserInformation> buildErrorResponse(CreateUserCommand command, String errorDetails, HttpStatus httpStatus, GenericResponseCodes genericResponseCodes, String responseDetails) {
        return Either.left(
                UseCaseErrorResponseMapper
                        .buildErrorResponse(
                                httpStatus,
                                genericResponseCodes,
                                responseDetails,
                                String.valueOf(httpStatus.getReasonPhrase()),
                                errorDetails,
                                command.getMessageUuid(),
                                command.getRequestAppId()));
    }

    private Either<UseCaseErrorResponse, CreateUserInformation> buildSuccessResponse(UsersDto userDto,
                                                                                     String messageUuid,
                                                                                     String requestAppId,
                                                                                     String responseDetails) {
        return Either.right(CreateUserUseCaseMapper.buildSuccessResponse(
                userDto,
                messageUuid,
                requestAppId,
                HttpStatus.CREATED,
                GenericResponseCodes.TRANSACCION_EXITOSA,
                responseDetails));
    }


}
