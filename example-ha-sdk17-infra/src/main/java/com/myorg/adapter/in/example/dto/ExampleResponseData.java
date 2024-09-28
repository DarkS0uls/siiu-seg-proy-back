package com.myorg.adapter.in.example.dto;

import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Builder
@Getter
public class ExampleResponseData implements Serializable {
    private String isoDateTime;
}
