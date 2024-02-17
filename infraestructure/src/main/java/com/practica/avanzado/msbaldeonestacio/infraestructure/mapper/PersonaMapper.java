package com.practica.avanzado.msbaldeonestacio.infraestructure.mapper;

import com.practica.avanzado.msbaldeonestacio.domain.aggregates.dto.PersonaDTO;
import com.practica.avanzado.msbaldeonestacio.infraestructure.entity.PersonaEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class PersonaMapper {

    private final ModelMapper modelMapper = new ModelMapper();

    public PersonaDTO mapperToPersonaDTO(PersonaEntity personaEntity) {
        return modelMapper.map(personaEntity, PersonaDTO.class);
    }

    public PersonaEntity mapperToPersonaEntity(PersonaDTO personaDTO) {
        return modelMapper.map(personaDTO, PersonaEntity.class);
    }
}
