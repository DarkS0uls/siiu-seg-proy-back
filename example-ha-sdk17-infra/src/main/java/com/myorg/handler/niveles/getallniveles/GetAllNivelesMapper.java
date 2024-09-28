package com.myorg.handler.niveles.getallniveles;

import com.myorg.adapter.in.niveles.dto.GetAllNivelesResponse;
import com.myorg.adapter.in.niveles.dto.GetAllNivelesResponseData;
import com.myorg.adapter.in.niveles.dto.NivelResponseData;
import com.myorg.adapter.in.util.GenericUserResponseData;
import com.myorg.adapter.in.util.HeaderObjectResponse;
import com.myorg.adapter.in.util.MessageObjectResponse;
import com.myorg.kernel.command.niveles.GetAllNivelesCommand;
import com.myorg.kernel.domain.in.niveles.GetAllNivelesInformation;
import lombok.experimental.UtilityClass;

@UtilityClass
public class GetAllNivelesMapper {

    public static GetAllNivelesCommand requestToCommand(String messageUuid, String requestAppId, Integer pageNumber, Integer pageSize) {
        return GetAllNivelesCommand
                .builder()
                .messageUuid(messageUuid)
                .requestAppId(requestAppId)
                .pageNumber(pageNumber)
                .pageSize(pageSize)
                .build();
    }

    public static GetAllNivelesResponse informationToResponse(GetAllNivelesInformation information) {
       return GetAllNivelesResponse
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
                       GetAllNivelesResponseData
                               .builder()
                               .niveles(
                                        information.getData().getNiveles().stream()
                                                  .map(nivel -> NivelResponseData
                                                          .builder()
                                                          .identificador(nivel.getIdentificador())
                                                          .nombre(nivel.getNombre())
                                                          .descripcion(nivel.getDescripcion())
                                                          .tipoNivel(nivel.getTipoNivel())
                                                          .build()

                                                  )
                                                  .toList()
                               )
                               .build()
               )
               .build();
    }


}
