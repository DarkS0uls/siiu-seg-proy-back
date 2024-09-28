package com.myorg.kernel.domain.in.example;

import com.myorg.kernel.domain.util.InformationPayload;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ExampleInformationPayload implements Serializable, InformationPayload {
    private String isoDateTime;
}
