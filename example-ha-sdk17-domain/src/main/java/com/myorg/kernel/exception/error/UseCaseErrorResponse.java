package com.myorg.kernel.exception.error;

import com.myorg.kernel.domain.util.HeaderObjectInformationResponse;
import com.myorg.kernel.domain.util.MessageObjectInformationResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.ArrayList;
import java.util.List;


@Builder
@AllArgsConstructor
public class UseCaseErrorResponse extends Exception {
    private final HeaderObjectInformationResponse headers;
    private final MessageObjectInformationResponse messageResponse;
    private final List<UseCaseErrorResponseError> errors;

    public UseCaseErrorResponse(HeaderObjectInformationResponse headers, MessageObjectInformationResponse messageResponse, UseCaseErrorResponseError error) {
        this.headers = headers;
        this.messageResponse = messageResponse;
        this.errors = new ArrayList<>();
        if (error != null) {
            this.errors.add(error);
        }
    }

    public HeaderObjectInformationResponse getHeaders() {
        return headers;
    }

    public MessageObjectInformationResponse getMessageResponse() {
        return messageResponse;
    }

    public List<UseCaseErrorResponseError> getErrors() {
        return errors;
    }
}
