package com.practica.avanzado.msbaldeonestacio.infraestructure.repository;

import com.practica.avanzado.msbaldeonestacio.infraestructure.entity.PersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonaRepository extends JpaRepository<PersonaEntity, Long> {

    Optional<PersonaEntity> findByNumDocu(String numDocu);
    List<PersonaEntity> findByEstadoEquals(Integer estado);
}
