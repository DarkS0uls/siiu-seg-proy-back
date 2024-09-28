package com.myorg.adapter.in.error;

import com.myorg.adapter.in.util.GenericResponse;
import com.myorg.adapter.in.util.HeaderObjectResponse;
import com.myorg.adapter.in.util.MessageObjectResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse implements GenericResponse, Serializable {
    private HeaderObjectResponse headers;
    private MessageObjectResponse messageResponse;
    private List<ErrorResponseError> errors;
}
