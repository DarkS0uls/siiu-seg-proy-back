package com.myorg.kernel.domain.in.users.getbyid;

import com.myorg.kernel.domain.util.AbstractInformationResponse;
import com.myorg.kernel.domain.util.GenericUserInformationPayload;
import com.myorg.kernel.domain.util.HeaderObjectInformationResponse;
import com.myorg.kernel.domain.util.MessageObjectInformationResponse;

public class GetUserByIdInformation extends AbstractInformationResponse<GenericUserInformationPayload> {

    public GetUserByIdInformation(HeaderObjectInformationResponse headers, MessageObjectInformationResponse messageResponse, GenericUserInformationPayload data) {
        super(headers, messageResponse, data);
    }
}
