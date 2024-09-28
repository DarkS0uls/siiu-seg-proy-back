package com.myorg.usecase.users.getbyid;

import com.myorg.kernel.command.users.CreateUserCommand;
import com.myorg.kernel.command.users.GetUSerByIdCommand;
import com.myorg.kernel.domain.in.users.create.CreateUserInformation;
import com.myorg.kernel.domain.in.users.getbyid.GetUserByIdInformation;
import com.myorg.kernel.domain.out.postgres.users.UsersDto;
import com.myorg.kernel.domain.util.GenericResponseCodes;
import com.myorg.kernel.domain.util.HttpStatus;
import com.myorg.kernel.exception.error.UseCaseErrorResponse;
import com.myorg.kernel.exception.error.mapper.UseCaseErrorResponseMapper;
import com.myorg.service.users.UsersMangerService;
import com.myorg.usecase.UseCase;
import com.myorg.usecase.users.create.mapper.CreateUserUseCaseMapper;
import com.myorg.usecase.users.getbyid.mapper.GetUserByIdUseCaseMapper;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Slf4j
public class GetUserByIdUseCase implements UseCase<GetUSerByIdCommand, Either<UseCaseErrorResponse, GetUserByIdInformation>> {

    private final UsersMangerService usersMangerService;
    @Override
    public Either<UseCaseErrorResponse, GetUserByIdInformation> execute(GetUSerByIdCommand command) {
        log.info("GetUserByIdUseCase.execute, get user by id: {}", command.getUuid());
        return usersMangerService.getUserByUuid(command.getUuid())
                .map(userVo -> {
                    if (userVo.getSuccess()) {
                        return buildSuccessResponse(
                                userVo.getPayload(),
                                command.getMessageUuid(),
                                command.getRequestAppId(),
                                String.format("User found with UUID: %s", userVo.getPayload().getUuid()));
                    } else {
                        return buildErrorResponse(
                                command,
                                userVo.getResponseMessage(),
                                HttpStatus.NOT_FOUND,
                                GenericResponseCodes.TRANSACCION_FALLIDA,
                                String.format("Error Getting User with UUID: %s",command.getUuid()));
                    }
                })
                .onErrorResume(e -> {
                    log.error("GetUserByIdUseCase.execute,Error getting user, with user uuid: {} and details:{}", command.getUuid(), e.getMessage());
                    return Mono.just(Either.left(
                            UseCaseErrorResponseMapper
                                    .buildErrorResponse(
                                            HttpStatus.INTERNAL_SERVER_ERROR,
                                            GenericResponseCodes.TRANSACCION_FALLIDA,
                                            "",
                                            String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()),
                                            "Error getting user",
                                            command.getMessageUuid(),
                                            command.getRequestAppId())));
                })
                .block();
    }

    private Either<UseCaseErrorResponse, GetUserByIdInformation> buildErrorResponse(GetUSerByIdCommand command, String errorDetails, HttpStatus httpStatus, GenericResponseCodes genericResponseCodes, String responseDetails) {
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

    private Either<UseCaseErrorResponse, GetUserByIdInformation> buildSuccessResponse(UsersDto userDto,
                                                                                     String messageUuid,
                                                                                     String requestAppId,
                                                                                     String responseDetails) {
        return Either.right(GetUserByIdUseCaseMapper.buildSuccessResponse(
                userDto,
                messageUuid,
                requestAppId,
                HttpStatus.OK,
                GenericResponseCodes.TRANSACCION_EXITOSA,
                responseDetails));
    }
}
