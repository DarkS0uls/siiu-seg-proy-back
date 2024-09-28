package com.myorg.adapter.in.example.dto;

import com.myorg.adapter.in.util.KeyStatusEnum;
import com.myorg.adapter.in.util.constrains.ValueOfEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ExampleRequest implements Serializable {

    private String name;
    @ValueOfEnum(enumClass = KeyStatusEnum.class, message = "Invalid status")
    private String status;
    private String email;

}
