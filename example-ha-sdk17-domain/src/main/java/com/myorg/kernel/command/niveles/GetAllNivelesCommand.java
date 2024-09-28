package com.myorg.kernel.command.niveles;

import com.myorg.kernel.command.Command;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class GetAllNivelesCommand implements Serializable, Command {
    private String messageUuid;
    private String requestAppId;
    private Integer pageNumber;
    private Integer pageSize;
}
