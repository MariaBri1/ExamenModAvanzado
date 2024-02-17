package com.practica.avanzado.msbaldeonestacio.domain.aggregates.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InformacionRequest {
    private String tipoDocumento;
    private String tipoPersona;
    private String numDocumento;
}
