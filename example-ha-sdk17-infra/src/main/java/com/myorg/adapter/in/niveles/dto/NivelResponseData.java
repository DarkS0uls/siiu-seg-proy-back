package com.myorg.adapter.in.niveles.dto;

import com.myorg.adapter.in.util.GenericResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class NivelResponseData implements Serializable, GenericResponse {

    private Integer identificador;
    private String nombre;
    private String descripcion;
    private String tipoNivel;

}
