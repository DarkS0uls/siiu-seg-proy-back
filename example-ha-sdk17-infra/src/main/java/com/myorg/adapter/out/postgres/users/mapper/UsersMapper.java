package com.myorg.adapter.out.postgres.users.mapper;

import com.myorg.adapter.out.postgres.users.entity.UsersEntity;
import com.myorg.adapter.out.postgres.users.entity.UsersStatusEnum;
import com.myorg.kernel.domain.out.postgres.users.UsersDto;
import com.myorg.kernel.domain.out.postgres.users.UsersMassiveDto;
import com.myorg.kernel.domain.util.GenericPaginationDto;
import lombok.experimental.UtilityClass;
import org.springframework.data.domain.Page;

import java.util.stream.Collectors;

@UtilityClass
public class UsersMapper {

    public static UsersDto entityToDto(UsersEntity entity) {
        return UsersDto.builder()
                .id(entity.getId())
                .uuid(entity.getUuid())
                .name(entity.getName())
                .secondName(entity.getSecondName())
                .lastname(entity.getLastname())
                .email(entity.getEmail())
                .cellphone(entity.getCellphone())
                .status(entity.getStatus().name())
                .createdDt(entity.getCreatedDt())
                .updateDt(entity.getUpdateDt())
                .build();
    }

    public static UsersEntity dtoToEntity(UsersDto dto) {
        return UsersEntity
                .builder()
                .id(dto.getId())
                .uuid(dto.getUuid())
                .name(dto.getName())
                .secondName(dto.getSecondName())
                .lastname(dto.getLastname())
                .email(dto.getEmail())
                .cellphone(dto.getCellphone())
                .status(UsersStatusEnum.fromString(dto.getStatus()))// enum value.
                .createdDt(dto.getCreatedDt())
                .updateDt(dto.getUpdateDt())
                .build();
    }

    public static UsersMassiveDto entityToMassiveDto(Page<UsersEntity> page, Integer pageSize, Integer pageNumber) {
        return UsersMassiveDto
                .builder()
                .users(
                        page.toList()
                                .stream()
                                .map(entity->
                                        UsersDto
                                                .builder()
                                                .id(entity.getId())
                                                .uuid(entity.getUuid())
                                                .name(entity.getName())
                                                .secondName(entity.getSecondName())
                                                .lastname(entity.getLastname())
                                                .email(entity.getEmail())
                                                .cellphone(entity.getCellphone())
                                                .status(entity.getStatus().name())
                                                .createdDt(entity.getCreatedDt())
                                                .updateDt(entity.getUpdateDt())
                                                .build()

                                )
                                .collect(Collectors.toList())
                )
                .pagination(
                        GenericPaginationDto
                                .builder()
                                .pageNumber(pageNumber)
                                .pageSize(pageSize)
                                .totalElement(page.getTotalElements())
                                .hasMoreElements(page.hasNext())
                                .build()
                )
                .build();
    }
}
