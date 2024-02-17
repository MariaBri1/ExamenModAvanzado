package com.practica.avanzado.msbaldeonestacio.domain.ports.out;

import com.practica.avanzado.msbaldeonestacio.domain.aggregates.dto.PersonaDTO;
import com.practica.avanzado.msbaldeonestacio.domain.aggregates.request.InformacionRequest;

import java.util.List;
import java.util.Optional;

public interface PersonaServiceOut {

    PersonaDTO registrarPersonaOut(InformacionRequest informacionRequest);

    Optional<PersonaDTO> obtenerPersonaOut(String numeroDocumento);

    List<PersonaDTO> obtenerPersonasActivasOut();

    PersonaDTO actualizarPersonaOut(Long idPersona, InformacionRequest informacionRequest);

    PersonaDTO eliminarPersonaOut(Long idPersona);
}
