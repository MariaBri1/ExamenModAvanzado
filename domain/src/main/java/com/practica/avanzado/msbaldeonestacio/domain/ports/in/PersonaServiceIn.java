package com.practica.avanzado.msbaldeonestacio.domain.ports.in;

import com.practica.avanzado.msbaldeonestacio.domain.aggregates.dto.PersonaDTO;
import com.practica.avanzado.msbaldeonestacio.domain.aggregates.request.InformacionRequest;

import java.util.List;
import java.util.Optional;

public interface PersonaServiceIn {

    PersonaDTO registrarPersonaIn(InformacionRequest informacionRequest);

    Optional<PersonaDTO> obtenerPersonaIn(String numeroDocumento);

    List<PersonaDTO> obtenerPersonasActivasIn();

    PersonaDTO actualizarPersonaIn(Long idPersona, InformacionRequest informacionRequest);

    PersonaDTO eliminarPersonaIn(Long idPersona);
}
