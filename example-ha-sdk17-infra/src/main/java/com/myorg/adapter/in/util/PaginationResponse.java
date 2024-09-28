package com.myorg.adapter.in.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PaginationResponse implements Serializable, GenericResponse{
    private Integer totalElement;
    private Integer pageSize;
    private Integer pageNumber;
    private Boolean hasMoreElements;
}
