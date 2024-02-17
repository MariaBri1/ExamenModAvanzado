package com.practica.avanzado.msbaldeonestacio.infraestructure.adapters;

import com.practica.avanzado.msbaldeonestacio.domain.aggregates.dto.PersonaDTO;
import com.practica.avanzado.msbaldeonestacio.domain.aggregates.request.InformacionRequest;
import com.practica.avanzado.msbaldeonestacio.domain.aggregates.response.ReniecResponse;
import com.practica.avanzado.msbaldeonestacio.domain.ports.out.PersonaServiceOut;
import com.practica.avanzado.msbaldeonestacio.infraestructure.entity.PersonaEntity;
import com.practica.avanzado.msbaldeonestacio.infraestructure.entity.TipoDocumentoEntity;
import com.practica.avanzado.msbaldeonestacio.infraestructure.entity.TipoPersonaEntity;
import com.practica.avanzado.msbaldeonestacio.infraestructure.mapper.PersonaMapper;
import com.practica.avanzado.msbaldeonestacio.infraestructure.repository.PersonaRepository;
import com.practica.avanzado.msbaldeonestacio.infraestructure.repository.TipoDocumentoRepository;
import com.practica.avanzado.msbaldeonestacio.infraestructure.repository.TipoPersonaRepository;
import com.practica.avanzado.msbaldeonestacio.infraestructure.rest.client.ReniecCliente;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.practica.avanzado.msbaldeonestacio.domain.aggregates.constants.Constants.*;

@Service
@RequiredArgsConstructor
public class PersonaAdapter implements PersonaServiceOut {

    private final ReniecCliente reniecCliente;
    private final PersonaRepository personaRepository;
    private final TipoDocumentoRepository tipoDocumentoRepository;
    private final TipoPersonaRepository tipoPersonaRepository;
    private final PersonaMapper personaMapper;

    @Value("${token.api}")
    private String token;

    @Override
    public PersonaDTO registrarPersonaOut(InformacionRequest informacionRequest) {
        ReniecResponse informacionPersona = obtenerInformacioPersona(informacionRequest.getNumDocumento());
        Optional<TipoDocumentoEntity> tipoDocumento = tipoDocumentoRepository.findBycodTipo(informacionRequest.getTipoDocumento());
        Optional<TipoPersonaEntity> tipoPersona = tipoPersonaRepository.findBycodTipo(informacionRequest.getTipoPersona());

        return personaMapper.mapperToPersonaDTO(personaRepository.save(this.construirPersonaEntityCrear(informacionPersona, tipoDocumento.get(), tipoPersona.get())));
    }

    @Override
    public Optional<PersonaDTO> obtenerPersonaOut(String numeroDocumento) {
        return Optional.ofNullable(personaMapper.mapperToPersonaDTO(personaRepository.findByNumDocu(numeroDocumento).get()));
    }

    @Override
    public List<PersonaDTO> obtenerPersonasActivasOut() {
        List<PersonaDTO> personasDTO = new ArrayList<>();
        List<PersonaEntity> personasEntities = personaRepository.findByEstadoEquals(1);

        for (PersonaEntity personaEntity : personasEntities) {
            personasDTO.add(personaMapper.mapperToPersonaDTO(personaEntity));
        }

        return personasDTO;
    }

    @Override
    public PersonaDTO actualizarPersonaOut(Long idPersona, InformacionRequest informacionRequest) {
        Optional<PersonaEntity> personaDeDB = personaRepository.findById(idPersona);

        if (personaDeDB.isEmpty()) {
            return null;
        }

        ReniecResponse informacionPersona = obtenerInformacioPersona(informacionRequest.getNumDocumento());

        return personaMapper.mapperToPersonaDTO(personaRepository.save(this.construirPersonaEntityActualizar(personaDeDB.get(), informacionPersona)));
    }

    @Override
    public PersonaDTO eliminarPersonaOut(Long idPersona) {
        Optional<PersonaEntity> personaDeDB = personaRepository.findById(idPersona);

        if (personaDeDB.isEmpty()) {
            return null;
        }

        PersonaEntity personaEliminada = personaDeDB.get();

        personaEliminada.setEstado(STATUS_INACTIVE);
        personaEliminada.setUsuaDelet(AUDIT_ADMIN);
        personaEliminada.setDateDelet(this.obtenerFechaDelSistema());

        return personaMapper.mapperToPersonaDTO(personaRepository.save(personaEliminada));
    }

    private ReniecResponse obtenerInformacioPersona(String numeroDocumento) {
        return reniecCliente.obtenerInformacion(numeroDocumento, BEARER.concat(token));
    }

    private Timestamp obtenerFechaDelSistema() {
        return new Timestamp(System.currentTimeMillis());
    }

    private PersonaEntity construirPersonaEntityCrear(ReniecResponse informacionPersona, TipoDocumentoEntity tipoDocumento, TipoPersonaEntity tipoPersona) {
        return PersonaEntity.builder()
                .numDocu(informacionPersona.getNumeroDocumento())
                .nombres(informacionPersona.getNombres())
                .apePat(informacionPersona.getApellidoPaterno())
                .apeMat(informacionPersona.getApellidoPaterno())
                .tipoDocumento(tipoDocumento)
                .tipoPersona(tipoPersona)
                .estado(STATUS_ACTIVE)
                .usuaCrea(AUDIT_ADMIN)
                .dateCreate(this.obtenerFechaDelSistema())
                .build();
    }

    private PersonaEntity construirPersonaEntityActualizar(PersonaEntity personaActualizar, ReniecResponse informacionPersona) {
        personaActualizar.setNumDocu(informacionPersona.getNumeroDocumento());
        personaActualizar.setNombres(informacionPersona.getNombres());
        personaActualizar.setApePat(informacionPersona.getApellidoPaterno());
        personaActualizar.setApeMat(informacionPersona.getApellidoMaterno());
        personaActualizar.setUsuaModif(AUDIT_ADMIN);
        personaActualizar.setDateModif(this.obtenerFechaDelSistema());

        return personaActualizar;
    }

}
