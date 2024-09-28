package com.myorg.kernel.domain.in.niveles;

import com.myorg.kernel.domain.util.InformationPayload;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class NivelInformationPayload implements Serializable, InformationPayload {

    private Integer identificador;
    private String nombre;
    private String descripcion;
    private String tipoNivel;
}
