package com.myorg.kernel.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ExampleCommand implements Command, Serializable {

    private String messageUuid;
    private String requestAppId;
}
