package com.practica.avanzado.msbaldeonestacio.domain.impl;

import com.practica.avanzado.msbaldeonestacio.domain.aggregates.dto.PersonaDTO;
import com.practica.avanzado.msbaldeonestacio.domain.aggregates.request.InformacionRequest;
import com.practica.avanzado.msbaldeonestacio.domain.ports.in.PersonaServiceIn;
import com.practica.avanzado.msbaldeonestacio.domain.ports.out.PersonaServiceOut;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonaServiceImpl implements PersonaServiceIn {

    private final PersonaServiceOut personaServiceOut;

    @Override
    public PersonaDTO registrarPersonaIn(InformacionRequest informacionRequest) {
        return personaServiceOut.registrarPersonaOut(informacionRequest);
    }

    @Override
    public Optional<PersonaDTO> obtenerPersonaIn(String numeroDocumento) {
        return personaServiceOut.obtenerPersonaOut(numeroDocumento);
    }

    @Override
    public List<PersonaDTO> obtenerPersonasActivasIn() {
        return personaServiceOut.obtenerPersonasActivasOut();
    }

    @Override
    public PersonaDTO actualizarPersonaIn(Long idPersona, InformacionRequest informacionRequest) {
        return personaServiceOut.actualizarPersonaOut(idPersona, informacionRequest);
    }

    @Override
    public PersonaDTO eliminarPersonaIn(Long idPersona) {
        return personaServiceOut.eliminarPersonaOut(idPersona);
    }
}
