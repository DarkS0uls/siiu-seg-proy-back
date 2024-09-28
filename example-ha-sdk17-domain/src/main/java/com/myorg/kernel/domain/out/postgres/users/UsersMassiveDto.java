package com.myorg.kernel.domain.out.postgres.users;

import com.myorg.kernel.domain.util.GenericPaginationDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UsersMassiveDto implements Serializable {
    private List<UsersDto> users;
    private GenericPaginationDto pagination;
    public List<UsersDto> getUsers() {
        if (this.users == null) {
            this.users = new ArrayList<>();
        }
        return this.users;
    }
}
