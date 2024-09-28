package com.myorg.adapter.in.users.getall.dto;

import com.myorg.adapter.in.util.GenericResponse;
import com.myorg.adapter.in.util.GenericUserResponseData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GetAllUsersResponsePayload implements Serializable, GenericResponse {

    private List<GenericUserResponseData> users;

    public List<GenericUserResponseData> getUsers() {
        if (users == null) {
            users = new ArrayList<>();
        }
        return users;
    }


}
