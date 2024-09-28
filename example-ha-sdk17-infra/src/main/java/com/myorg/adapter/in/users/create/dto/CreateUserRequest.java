package com.myorg.adapter.in.users.create.dto;


import com.myorg.adapter.in.util.UserStatusEnum;
import com.myorg.adapter.in.util.constrains.ValueOfEnum;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Validated
public class CreateUserRequest implements Serializable {

    @NotNull(message = "userName cannot be null")
    @Size(min = 1, max = 50, message = "userName must be between 1 and 50 characters")
    private String userName;

    @Size(min = 1, max = 50, message = "secondName must be between 1 and 50 characters")
    private String secondName;

    @NotNull(message = "lastName cannot be null")
    @Size(min = 1, max = 50, message = "lastName must be between 1 and 50 characters")
    private String lastName;

    @Size(min = 1, max = 50, message = "secondLastName must be between 1 and 50 characters")
    private String email;
    @Size(min = 13, max = 13, message = "cellphone must be 13 characters")
    private String cellphone;

    @NotNull(message = "status cannot be null")
    @ValueOfEnum(enumClass = UserStatusEnum.class, message = "status must be either ACTIVE or INACTIVE")
    private String status;

}
