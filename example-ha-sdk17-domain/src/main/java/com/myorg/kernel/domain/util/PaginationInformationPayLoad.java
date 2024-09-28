package com.myorg.kernel.domain.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaginationInformationPayLoad implements Serializable {
    private Integer totalElement;
    private Integer pageSize;
    private Integer pageNumber;
    private Boolean hasMoreElements;
}
