package com.myorg.usecase;

import com.myorg.kernel.command.ExampleCommand;
import com.myorg.kernel.domain.in.example.ExampleInformation;
import com.myorg.kernel.domain.in.example.ExampleInformationPayload;
import com.myorg.kernel.domain.util.GenericResponseCodes;
import com.myorg.kernel.domain.util.HeaderObjectInformationResponse;
import com.myorg.kernel.domain.util.HttpStatus;
import com.myorg.kernel.domain.util.MessageObjectInformationResponse;
import com.myorg.kernel.exception.error.UseCaseErrorResponse;
import com.myorg.service.time.TimeManagerService;
import io.vavr.control.Either;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
public class ExampleUseCase implements UseCase<ExampleCommand, Either<UseCaseErrorResponse, ExampleInformation>>{

    private final TimeManagerService timeManagerService;

    @Override
    public Either<UseCaseErrorResponse, ExampleInformation> execute(ExampleCommand command) {
        log.info("Executing ExampleUseCase");
        return Either.right(
                buildSuccessResponse(
                        command,
                        HttpStatus.OK,
                        GenericResponseCodes.TRANSACCION_EXITOSA,
                        "Success get Iso Datetime",
                        ExampleInformationPayload
                                .builder()
                                .isoDateTime(timeManagerService.getInstantIsoFormat())
                                .build()
                )
        );
    }

    private ExampleInformation buildSuccessResponse(ExampleCommand command,
                                                    HttpStatus httpStatus,
                                                    GenericResponseCodes genericResponseCodes,
                                                    String responseDetails,
                                                    ExampleInformationPayload payload) {
        log.info("Building success response");
        return new ExampleInformation(
                HeaderObjectInformationResponse
                        .builder()
                        .messageUuid(command.getMessageUuid())
                        .requestAppId(command.getRequestAppId())
                        .httpStatusCode(httpStatus.value())
                        .httpStatusDesc(httpStatus.getReasonPhrase())
                        .build(),
                MessageObjectInformationResponse
                        .builder()
                        .responseCode(genericResponseCodes.getValue())
                        .responseMessage(genericResponseCodes.getDescription())
                        .responseDetail(responseDetails)
                        .build(),
               payload
        );
    }
}
