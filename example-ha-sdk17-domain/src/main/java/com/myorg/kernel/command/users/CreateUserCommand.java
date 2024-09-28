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
public class CreateUserCommand implements Serializable, Command {

    private String messageUuid;
    private String requestAppId;
    private String userName;
    private String secondName;
    private String lastName;
    private String email;
    private String cellphone;
    private String status;
}
