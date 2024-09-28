package com.myorg.handler.users.getall.mapper;

import com.myorg.adapter.in.users.getall.dto.GetAllUsersResponse;
import com.myorg.adapter.in.users.getall.dto.GetAllUsersResponsePayload;
import com.myorg.adapter.in.util.GenericUserResponseData;
import com.myorg.adapter.in.util.HeaderObjectResponse;
import com.myorg.adapter.in.util.MessageObjectResponse;
import com.myorg.adapter.in.util.PaginationResponse;
import com.myorg.kernel.command.users.GetAllUsersCommand;
import com.myorg.kernel.domain.in.users.getall.GetAllUsersInformation;
import lombok.experimental.UtilityClass;

import java.util.stream.Collectors;

@UtilityClass
public class GetAllUsersMapper {

    public GetAllUsersCommand requestToCommand(String messageUuid,
                                               String requestAppId,
                                               Integer pageNumber,
                                               Integer pageSize,
                                               String status,
                                               String userName,
                                               String cellphone,
                                               String createdDt) {
        return GetAllUsersCommand
                .builder()
                .messageUuid(messageUuid)
                .requestAppId(requestAppId)
                .pageNumber(pageNumber)
                .pageSize(pageSize)
                .status(status)
                .userName(userName)
                .cellphone(cellphone)
                .createdDt(createdDt)
                .build();
    }

    public static GetAllUsersResponse informationToResponse(GetAllUsersInformation information) {
        return GetAllUsersResponse
                .builder()
                .headers(
                        HeaderObjectResponse
                                .builder()
                                .httpStatusCode(information.getHeaders().getHttpStatusCode())
                                .httpStatusDesc(information.getHeaders().getHttpStatusDesc())
                                .messageUuid(information.getHeaders().getMessageUuid())
                                .requestDatetime(information.getHeaders().getRequestDatetime())
                                .requestAppId(information.getHeaders().getRequestAppId())
                                .build()
                )
                .messageResponse(
                        MessageObjectResponse
                                .builder()
                                .responseCode(information.getMessageResponse().getResponseCode())
                                .responseMessage(information.getMessageResponse().getResponseMessage())
                                .responseDetails(information.getMessageResponse().getResponseDetail())
                                .build()
                )
                .data(
                        GetAllUsersResponsePayload
                                .builder()
                                .users(
                                        information.getData().getUsers()
                                                .stream()
                                                .map(informationUser ->
                                                        GenericUserResponseData
                                                                .builder()
                                                                .uuid(informationUser.getUuid())
                                                                .userName(informationUser.getUserName())
                                                                .secondName(informationUser.getSecondName())
                                                                .lastName(informationUser.getLastName())
                                                                .email(informationUser.getEmail())
                                                                .cellphone(informationUser.getCellphone())
                                                                .status(informationUser.getStatus())
                                                                .createDt(informationUser.getCreateDt())
                                                                .updateDt(informationUser.getUpdateDt())
                                                                .build()
                                                ).toList()
                                ).build()
                )
                .pagination(
                        PaginationResponse
                                .builder()
                                .pageNumber(information.getPagination().getPageNumber())
                                .pageSize(information.getPagination().getPageSize())
                                .totalElement(information.getPagination().getTotalElement())
                                .hasMoreElements(information.getPagination().getHasMoreElements())
                                .build()
                )
                .build();
    }
}
