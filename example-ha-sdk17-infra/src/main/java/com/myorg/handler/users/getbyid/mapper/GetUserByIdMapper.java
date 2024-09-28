package com.myorg.handler.users.getbyid.mapper;

import com.myorg.adapter.in.users.create.dto.CreateUserResponse;
import com.myorg.adapter.in.users.getbyid.dto.GetUserByIdResponse;
import com.myorg.adapter.in.util.GenericUserResponseData;
import com.myorg.adapter.in.util.HeaderObjectResponse;
import com.myorg.adapter.in.util.MessageObjectResponse;
import com.myorg.kernel.command.users.GetUSerByIdCommand;
import com.myorg.kernel.domain.in.users.getbyid.GetUserByIdInformation;
import lombok.experimental.UtilityClass;

@UtilityClass
public class GetUserByIdMapper {

    public static GetUSerByIdCommand requestToCommand(String messageUuid, String requestAppId, String uuid) {
        return GetUSerByIdCommand
                .builder()
                .messageUuid(messageUuid)
                .requestAppId(requestAppId)
                .uuid(uuid)
                .build();
    }

    public static GetUserByIdResponse informationToResponse(GetUserByIdInformation information) {
        return GetUserByIdResponse
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
                        GenericUserResponseData
                                .builder()
                                .uuid(information.getData().getUuid())
                                .userName(information.getData().getUserName())
                                .secondName(information.getData().getSecondName())
                                .lastName(information.getData().getLastName())
                                .email(information.getData().getEmail())
                                .cellphone(information.getData().getCellphone())
                                .status(information.getData().getStatus())
                                .createDt(information.getData().getCreateDt())
                                .updateDt(information.getData().getUpdateDt())
                                .build()
                )
                .build();

    }

}
