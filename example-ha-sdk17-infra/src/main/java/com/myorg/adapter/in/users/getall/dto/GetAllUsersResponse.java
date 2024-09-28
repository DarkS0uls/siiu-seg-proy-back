package com.myorg.adapter.in.users.getall.dto;

import com.myorg.adapter.in.util.GenericResponse;
import com.myorg.adapter.in.util.HeaderObjectResponse;
import com.myorg.adapter.in.util.MessageObjectResponse;
import com.myorg.adapter.in.util.PaginationResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GetAllUsersResponse implements Serializable, GenericResponse {
    private HeaderObjectResponse headers;
    private MessageObjectResponse messageResponse;
    private GetAllUsersResponsePayload data;
    private PaginationResponse pagination;
}
