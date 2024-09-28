package com.myorg.kernel.domain.in.users.getall;

import com.myorg.kernel.domain.util.GenericUserInformationPayload;
import com.myorg.kernel.domain.util.InformationPayload;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetAllUsersInformationPayload implements Serializable, InformationPayload {
    private List<GenericUserInformationPayload> users;

    public List<GenericUserInformationPayload> getUsers() {
        if (users == null) {
            users = new ArrayList<>();
        }
        return users;
    }
}
