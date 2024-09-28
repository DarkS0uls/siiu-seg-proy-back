package com.myorg.kernel.domain.in.users.getall;

import com.myorg.kernel.domain.util.AbstractInformationMassiveResponse;
import com.myorg.kernel.domain.util.HeaderObjectInformationResponse;
import com.myorg.kernel.domain.util.MessageObjectInformationResponse;
import com.myorg.kernel.domain.util.PaginationInformationPayLoad;

public class GetAllUsersInformation extends AbstractInformationMassiveResponse<GetAllUsersInformationPayload> {
    public GetAllUsersInformation(HeaderObjectInformationResponse headers,
                                  MessageObjectInformationResponse messageResponse,
                                  GetAllUsersInformationPayload data,
                                  PaginationInformationPayLoad pagination) {
        super(headers, messageResponse, data, pagination);
    }
}
