package com.myorg.kernel.domain.out.postgres.users;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UsersDto implements Serializable {
    private Integer id;
    private UUID uuid;
    private String name;
    private String secondName;
    private String lastname;
    private String email;
    private String cellphone;
    private String status;
    private LocalDateTime createdDt;
    private LocalDateTime updateDt;
}
