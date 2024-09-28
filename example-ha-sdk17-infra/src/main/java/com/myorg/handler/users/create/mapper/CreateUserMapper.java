package com.myorg.handler.users.create.mapper;

import com.myorg.adapter.in.users.create.dto.CreateUserRequest;
import com.myorg.adapter.in.users.create.dto.CreateUserResponse;
import com.myorg.adapter.in.util.GenericUserResponseData;
import com.myorg.adapter.in.util.HeaderObjectResponse;
import com.myorg.adapter.in.util.MessageObjectResponse;
import com.myorg.kernel.command.users.CreateUserCommand;
import com.myorg.kernel.domain.in.users.create.CreateUserInformation;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CreateUserMapper {
    public static CreateUserCommand requestToCommand(String messageUuid, String requestAppId, CreateUserRequest request) {
        return CreateUserCommand
                .builder()
                .messageUuid(messageUuid)
                .requestAppId(requestAppId)
                .userName(request.getUserName())
                .secondName(request.getSecondName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .cellphone(request.getCellphone())
                .status(request.getStatus())
                .build();
    }

    public static CreateUserResponse informationToResponse(CreateUserInformation information) {
        return CreateUserResponse
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
