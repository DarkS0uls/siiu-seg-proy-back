package com.myorg.kernel.command.users;

import com.myorg.kernel.command.Command;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GetUSerByIdCommand implements Serializable, Command {
    private String messageUuid;
    private String requestAppId;
    private String uuid;
}
