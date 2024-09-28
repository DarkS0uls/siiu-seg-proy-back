package com.myorg.adapter.in.example.dto;

import com.myorg.adapter.in.util.GenericResponse;
import com.myorg.adapter.in.util.HeaderObjectResponse;
import com.myorg.adapter.in.util.MessageObjectResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ExampleResponse implements Serializable, GenericResponse {
    private HeaderObjectResponse headers;
    private MessageObjectResponse messageResponse;
    private ExampleResponseData data;
}
