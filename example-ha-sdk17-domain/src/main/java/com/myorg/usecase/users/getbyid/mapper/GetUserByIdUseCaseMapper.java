package com.myorg.usecase.users.getbyid.mapper;

import com.myorg.kernel.domain.in.users.create.CreateUserInformation;
import com.myorg.kernel.domain.in.users.getbyid.GetUserByIdInformation;
import com.myorg.kernel.domain.out.postgres.users.UsersDto;
import com.myorg.kernel.domain.util.*;
import lombok.experimental.UtilityClass;

@UtilityClass
public class GetUserByIdUseCaseMapper {
    public static GetUserByIdInformation buildSuccessResponse(
            UsersDto userDto,
            String messageUuid,
            String requestAppId,
            HttpStatus httpStatus,
            GenericResponseCodes responseCode,
            String responseDetails) {
        return new GetUserByIdInformation(
                HeaderObjectInformationResponse
                        .builder()
                        .messageUuid(messageUuid)
                        .requestAppId(requestAppId)
                        .httpStatusCode(httpStatus.value())
                        .httpStatusDesc(httpStatus.getReasonPhrase())
                        .build(),
                MessageObjectInformationResponse
                        .builder()
                        .responseCode(responseCode.getValue())
                        .responseMessage(responseCode.getDescription())
                        .responseDetail(responseDetails)
                        .build(),
                GenericUserInformationPayload
                        .builder()
                        .uuid(userDto.getUuid().toString())
                        .userName(userDto.getName())
                        .secondName(userDto.getSecondName())
                        .lastName(userDto.getLastname())
                        .email(userDto.getEmail())
                        .cellphone(userDto.getCellphone())
                        .status(userDto.getStatus())
                        .createDt(String.valueOf(userDto.getCreatedDt()))
                        .updateDt(String.valueOf(userDto.getUpdateDt()))
                        .build()
        );
    }
}
