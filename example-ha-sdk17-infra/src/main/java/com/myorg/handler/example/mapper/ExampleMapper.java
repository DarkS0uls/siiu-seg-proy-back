package com.myorg.handler.example.mapper;

import com.myorg.adapter.in.example.dto.ExampleResponse;
import com.myorg.adapter.in.example.dto.ExampleResponseData;
import com.myorg.adapter.in.util.HeaderObjectResponse;
import com.myorg.adapter.in.util.MessageObjectResponse;
import com.myorg.kernel.command.ExampleCommand;
import com.myorg.kernel.domain.in.example.ExampleInformation;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ExampleMapper {

    public static ExampleCommand requestToCommand(String messageUuid, String requestAppId) {
        return ExampleCommand
                .builder()
                .messageUuid(messageUuid)
                .requestAppId(requestAppId)
                .build();
    }

    public static ExampleResponse informationToResponse(ExampleInformation information) {
        return ExampleResponse
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
                        ExampleResponseData
                                .builder()
                                .isoDateTime(information.getData().getIsoDateTime())
                                .build()
                ).build();
    }
}
