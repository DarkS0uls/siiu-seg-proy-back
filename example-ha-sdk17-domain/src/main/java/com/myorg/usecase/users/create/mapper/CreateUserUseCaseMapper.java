package com.myorg.usecase.users.create.mapper;

import com.myorg.kernel.command.users.CreateUserCommand;
import com.myorg.kernel.domain.in.users.create.CreateUserInformation;
import com.myorg.kernel.domain.util.GenericUserInformationPayload;
import com.myorg.kernel.domain.out.postgres.users.UsersDto;
import com.myorg.kernel.domain.util.GenericResponseCodes;
import com.myorg.kernel.domain.util.HeaderObjectInformationResponse;
import com.myorg.kernel.domain.util.HttpStatus;
import com.myorg.kernel.domain.util.MessageObjectInformationResponse;
import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;
import java.util.UUID;

@UtilityClass
public class CreateUserUseCaseMapper {

    public static UsersDto commandToDto(CreateUserCommand command, UUID uuid, LocalDateTime createDt) {
        return UsersDto
                .builder()
                .uuid(uuid)
                .name(command.getUserName())
                .secondName(command.getSecondName())
                .lastname(command.getLastName())
                .status(command.getStatus())
                .email(command.getEmail())
                .cellphone(command.getCellphone())
                .createdDt(createDt)
                .updateDt(createDt)
                .build();
    }

    public static CreateUserInformation buildSuccessResponse(
            UsersDto userDto,
            String messageUuid,
            String requestAppId,
            HttpStatus httpStatus,
            GenericResponseCodes responseCode,
            String responseDetails) {
        return new CreateUserInformation(
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
