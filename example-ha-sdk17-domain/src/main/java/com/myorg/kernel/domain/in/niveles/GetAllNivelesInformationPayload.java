package com.myorg.kernel.domain.in.niveles;

import com.myorg.kernel.domain.util.InformationPayload;
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
public class GetAllNivelesInformationPayload implements Serializable, InformationPayload {

    private List<NivelInformationPayload> niveles;
}
