package com.practica.avanzado.msbaldeonestacio.infraestructure.repository;

import com.practica.avanzado.msbaldeonestacio.infraestructure.entity.TipoDocumentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TipoDocumentoRepository extends JpaRepository<TipoDocumentoEntity, Long> {

    Optional<TipoDocumentoEntity> findBycodTipo(String codTipo);
}
