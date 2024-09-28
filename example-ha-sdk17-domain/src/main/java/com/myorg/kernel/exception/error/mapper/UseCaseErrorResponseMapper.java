package com.myorg.kernel.exception.error.mapper;

import com.myorg.kernel.domain.util.GenericResponseCodes;
import com.myorg.kernel.domain.util.HeaderObjectInformationResponse;
import com.myorg.kernel.domain.util.HttpStatus;
import com.myorg.kernel.domain.util.MessageObjectInformationResponse;
import com.myorg.kernel.exception.error.UseCaseErrorResponse;
import com.myorg.kernel.exception.error.UseCaseErrorResponseError;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UseCaseErrorResponseMapper {
    public static UseCaseErrorResponse buildErrorResponse(
            HttpStatus status,
            GenericResponseCodes responseCode,
            String responseDetail,
            String errorCode,
            String errorDetail,
            String messageUuid,
            String requestAppId) {

        return new UseCaseErrorResponse(
                HeaderObjectInformationResponse
                        .builder()
                        .httpStatusCode(status.value())
                        .httpStatusDesc(status.name())
                        .messageUuid(messageUuid)
                        .requestAppId(requestAppId)
                        .build(),
                MessageObjectInformationResponse
                        .builder()
                        .responseCode(responseCode.getValue())
                        .responseMessage(responseCode.getDescription())
                        .responseDetail(responseDetail)
                        .build(),
                UseCaseErrorResponseError
                        .builder()
                        .errorCode(errorCode)
                        .errorDetail(errorDetail)
                        .build()
        );
    }
}
