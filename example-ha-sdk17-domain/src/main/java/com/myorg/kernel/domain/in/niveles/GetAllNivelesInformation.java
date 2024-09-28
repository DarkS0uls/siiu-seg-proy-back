package com.myorg.kernel.domain.in.niveles;

import com.myorg.kernel.domain.util.*;

public class GetAllNivelesInformation extends AbstractInformationMassiveResponse<GetAllNivelesInformationPayload> {

    public GetAllNivelesInformation(HeaderObjectInformationResponse headers, MessageObjectInformationResponse messageResponse, GetAllNivelesInformationPayload data, PaginationInformationPayLoad pagination) {
        super(headers, messageResponse, data, pagination);
    }
}
