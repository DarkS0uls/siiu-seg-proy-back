package com.myorg.kernel.domain.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class GenericPaginationDto implements Serializable {
    private Long totalElement;
    private Integer pageSize;
    private Integer pageNumber;
    private Boolean hasMoreElements;
}
