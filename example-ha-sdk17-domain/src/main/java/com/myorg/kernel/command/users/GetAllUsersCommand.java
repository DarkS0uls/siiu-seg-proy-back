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
public class GetAllUsersCommand implements Command, Serializable {
    private String messageUuid;
    private String requestAppId;
    private Integer pageNumber;
    private Integer pageSize;
    private String userName;
    private String cellphone;
    private String status;
    private String createdDt;
}
