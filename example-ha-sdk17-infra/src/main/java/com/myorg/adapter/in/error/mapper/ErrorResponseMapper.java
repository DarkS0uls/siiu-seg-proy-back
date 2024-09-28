package com.myorg.adapter.in.error.mapper;

import com.myorg.adapter.in.error.ErrorResponse;
import com.myorg.adapter.in.error.ErrorResponseError;
import com.myorg.adapter.in.util.HeaderObjectResponse;
import com.myorg.adapter.in.util.MessageObjectResponse;
import com.myorg.kernel.exception.error.UseCaseErrorResponse;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ErrorResponseMapper {

    public static ErrorResponse useCaseErrorToResponse(UseCaseErrorResponse error) {
        return ErrorResponse
                .builder()
                .headers(
                        HeaderObjectResponse
                                .builder()
                                .httpStatusCode(error.getHeaders().getHttpStatusCode())
                                .httpStatusDesc(error.getHeaders().getHttpStatusDesc())
                                .messageUuid(error.getHeaders().getMessageUuid())
                                .requestAppId(error.getHeaders().getRequestAppId())
                                .requestDatetime(error.getHeaders().getRequestDatetime())
                                .build()
                )
                .messageResponse(
                        MessageObjectResponse
                                .builder()
                                .responseCode(error.getMessageResponse().getResponseCode())
                                .responseMessage(error.getMessageResponse().getResponseMessage())
                                .responseDetails(error.getMessageResponse().getResponseDetail())
                                .build()
                )
                .errors(
                        error.getErrors()
                                .stream()
                                .map(object ->
                                        ErrorResponseError
                                                .builder()
                                                .errorCode(object.getErrorCode())
                                                .errorDetail(object.getErrorDetail())
                                                .build()
                                )
                                .toList()

                )
                .build();

    }


}
