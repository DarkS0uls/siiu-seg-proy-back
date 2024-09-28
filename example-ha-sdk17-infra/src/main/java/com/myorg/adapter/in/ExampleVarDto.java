package com.myorg.adapter.in;

import com.myorg.ports.UsersPort;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@AllArgsConstructor
@Getter

public class ExampleVarDto {
    String clientId;
    String projectVersion;
    String traceId;
    String spanId;
    UsersPort usersPort;

}
