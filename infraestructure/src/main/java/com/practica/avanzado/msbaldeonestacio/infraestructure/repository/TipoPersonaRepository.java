package com.practica.avanzado.msbaldeonestacio.infraestructure.repository;

import com.practica.avanzado.msbaldeonestacio.infraestructure.entity.TipoPersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TipoPersonaRepository extends JpaRepository<TipoPersonaEntity, Long> {

    Optional<TipoPersonaEntity> findBycodTipo(String codTipo);
}
