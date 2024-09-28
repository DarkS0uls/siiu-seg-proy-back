package com.myorg.usecase.users.getall;

import com.myorg.kernel.command.users.GetAllUsersCommand;
import com.myorg.kernel.domain.in.users.getall.GetAllUsersInformation;
import com.myorg.kernel.domain.out.postgres.users.UsersMassiveDto;
import com.myorg.kernel.domain.util.GenericResponseCodes;
import com.myorg.kernel.domain.util.HttpStatus;
import com.myorg.kernel.exception.error.UseCaseErrorResponse;
import com.myorg.kernel.exception.error.mapper.UseCaseErrorResponseMapper;
import com.myorg.service.time.TimeManagerService;
import com.myorg.service.users.UsersMangerService;
import com.myorg.usecase.UseCase;
import com.myorg.usecase.users.getall.mapper.GetAllUsersUseCaseMapper;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Slf4j
public class GetAllUsersUseCase implements UseCase<GetAllUsersCommand, Either<UseCaseErrorResponse, GetAllUsersInformation>> {

    private final TimeManagerService timeManagerService;
    private final UsersMangerService usersMangerService;

    @Override
    public Either<UseCaseErrorResponse, GetAllUsersInformation> execute(GetAllUsersCommand command) {
        log.info("GetAllUsersUseCase.execute,Getting all users with pageNumber: {}, pageSize: {}, status: {}, userName: {}, cellphone: {}", command.getPageNumber(), command.getPageSize(), command.getStatus(), command.getUserName(), command.getCellphone());
        return usersMangerService.getAllUsers(command.getPageNumber(),
                        command.getPageSize(),
                        command.getStatus(),
                        command.getUserName(),
                        command.getCellphone(),
                        timeManagerService.getLocalDateTimeIsoFormat(command.getCreatedDt())
                )
                .map(abstracVo -> {
                    if (abstracVo.getSuccess()) {
                        return buildSuccessResponse(
                                abstracVo.getPayload(),
                                command.getMessageUuid(),
                                command.getRequestAppId(),
                                "Success Getting All Users");
                    } else {
                        return buildErrorResponse(
                                command,
                                abstracVo.getResponseMessage(),
                                HttpStatus.BAD_GATEWAY,
                                GenericResponseCodes.TRANSACCION_FALLIDA,
                                "Error Getting Users");
                    }
                })
                .onErrorResume(e -> {
                    log.error("GetAllUsersUseCase.execute,Error getting all users, error: {}", e.getMessage());
                    return Mono.just(buildErrorResponse(
                            command,
                            "Error creating user",
                            HttpStatus.INTERNAL_SERVER_ERROR,
                            GenericResponseCodes.TRANSACCION_FALLIDA,
                            ""));
                })
                .block();

    }

    private Either<UseCaseErrorResponse, GetAllUsersInformation> buildErrorResponse(GetAllUsersCommand command, String errorDetails, HttpStatus httpStatus, GenericResponseCodes genericResponseCodes, String responseDetails) {
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

    private Either<UseCaseErrorResponse, GetAllUsersInformation> buildSuccessResponse(UsersMassiveDto usersMassiveDto,
                                                                                      String messageUuid,
                                                                                      String requestAppId,
                                                                                      String responseDetails) {
        return Either.right(GetAllUsersUseCaseMapper.buildSuccessResponse(
                usersMassiveDto,
                messageUuid,
                requestAppId,
                HttpStatus.OK,
                GenericResponseCodes.TRANSACCION_EXITOSA,
                responseDetails));
    }


}
