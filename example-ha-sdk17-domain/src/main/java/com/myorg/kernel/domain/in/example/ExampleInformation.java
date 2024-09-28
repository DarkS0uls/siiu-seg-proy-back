package com.myorg.kernel.domain.in.example;

import com.myorg.kernel.domain.util.AbstractInformationResponse;
import com.myorg.kernel.domain.util.HeaderObjectInformationResponse;
import com.myorg.kernel.domain.util.MessageObjectInformationResponse;

public class ExampleInformation extends AbstractInformationResponse<ExampleInformationPayload>{
    public ExampleInformation(HeaderObjectInformationResponse headers, MessageObjectInformationResponse messageResponse, ExampleInformationPayload data) {
        super(headers, messageResponse, data);
    }
}
