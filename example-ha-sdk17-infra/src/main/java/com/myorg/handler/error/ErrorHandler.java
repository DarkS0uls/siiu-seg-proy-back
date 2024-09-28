package com.myorg.handler.error;

import com.myorg.adapter.in.error.ErrorResponse;
import com.myorg.adapter.in.error.ErrorResponseError;
import com.myorg.adapter.in.util.GenericResponse;
import com.myorg.adapter.in.util.HeaderObjectResponse;
import com.myorg.adapter.in.util.MessageObjectResponse;
import com.myorg.kernel.domain.util.GenericResponseCodes;
import com.myorg.service.time.TimeManagerService;
import lombok.experimental.UtilityClass;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@UtilityClass
public class ErrorHandler {

    public static Mono<ResponseEntity<GenericResponse>> handlerError(
            final Throwable error) {
        return Mono.just(ResponseEntity.status(INTERNAL_SERVER_ERROR).body(unExpectedError(error.getMessage())));
    }

    private static ErrorResponse unExpectedError(String error) {
        return
                ErrorResponse
                        .builder()
                        .headers(
                                HeaderObjectResponse
                                        .builder()
                                        .httpStatusCode(INTERNAL_SERVER_ERROR.value())
                                        .httpStatusDesc(INTERNAL_SERVER_ERROR.name())
                                        .requestDatetime(new TimeManagerService().getInstantIsoFormat())
                                        .build()
                        )
                        .messageResponse(
                                MessageObjectResponse
                                        .builder()
                                        .responseCode(GenericResponseCodes.TRANSACCION_FALLIDA.getValue())
                                        .responseMessage(GenericResponseCodes.TRANSACCION_FALLIDA.getDescription())
                                        .responseDetails(INTERNAL_SERVER_ERROR.getReasonPhrase())
                                        .build()
                        )
                        .errors(

                                getNullError(INTERNAL_SERVER_ERROR.name(), "Unknown Error:" + error)
                        )
                        .build();
    }

    private static List<ErrorResponseError> getNullError(String code, String detail) {
        List<ErrorResponseError> responseErrorsList = new ArrayList<>();
        responseErrorsList.add(ErrorResponseError
                .builder()
                .errorCode(code)
                .errorDetail(detail)
                .build());
        return responseErrorsList;
    }

}
