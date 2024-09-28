package com.myorg.adapter.in.niveles.dto;

import com.myorg.adapter.in.util.GenericResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class GetAllNivelesResponseData implements Serializable, GenericResponse {

    private List<NivelResponseData> niveles;
}
