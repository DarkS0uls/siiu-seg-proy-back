package com.myorg.usecase.users.getall.mapper;

import com.myorg.kernel.domain.in.users.getall.GetAllUsersInformation;
import com.myorg.kernel.domain.in.users.getall.GetAllUsersInformationPayload;
import com.myorg.kernel.domain.out.postgres.users.UsersMassiveDto;
import com.myorg.kernel.domain.util.*;
import lombok.experimental.UtilityClass;

@UtilityClass
public class GetAllUsersUseCaseMapper {

    public static GetAllUsersInformation buildSuccessResponse(
            UsersMassiveDto usersMassiveDto,
            String messageUuid,
            String requestAppId,
            HttpStatus httpStatus,
            GenericResponseCodes responseCode,
            String responseDetails) {
        return new GetAllUsersInformation(
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
                GetAllUsersInformationPayload
                        .builder()
                        .users(
                                usersMassiveDto.getUsers()
                                        .stream()
                                        .map(userDto ->
                                                GenericUserInformationPayload
                                                        .builder()
                                                        .uuid(userDto.getUuid().toString())
                                                        .userName(userDto.getName())
                                                        .secondName(userDto.getSecondName())
                                                        .lastName(userDto.getLastname())
                                                        .status(userDto.getStatus())
                                                        .email(userDto.getEmail())
                                                        .cellphone(userDto.getCellphone())
                                                        .createDt(userDto.getCreatedDt().toString())
                                                        .updateDt(userDto.getUpdateDt().toString())
                                                        .build()
                                        ).toList()
                        )
                        .build(),
                PaginationInformationPayLoad
                        .builder()
                        .totalElement((usersMassiveDto.getPagination().getTotalElement().intValue()))
                        .hasMoreElements(usersMassiveDto.getPagination().getHasMoreElements())
                        .pageSize(usersMassiveDto.getPagination().getPageSize())
                        .pageNumber(usersMassiveDto.getPagination().getPageNumber())
                        .build()

        );
    }
}
