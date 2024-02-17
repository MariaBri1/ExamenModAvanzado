package com.practica.avanzado.msbaldeonestacio.application.controller;

import com.practica.avanzado.msbaldeonestacio.domain.aggregates.dto.PersonaDTO;
import com.practica.avanzado.msbaldeonestacio.domain.aggregates.request.InformacionRequest;
import com.practica.avanzado.msbaldeonestacio.domain.ports.in.PersonaServiceIn;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@OpenAPIDefinition(
        info=@Info(
                title = "API-PERSONA",
                version = "1.0",
                description = "Mantenimiento de una persona"
        )
)
@RestController
@RequiredArgsConstructor
@RequestMapping("/v2/persona")
public class PersonaController {

    private final PersonaServiceIn personaService;

    @PostMapping
    @Operation(summary = "Crea una Persona")
    public ResponseEntity<PersonaDTO> registrarPersona(@RequestBody InformacionRequest informacionRequest) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(personaService.registrarPersonaIn(informacionRequest));
    }

    @GetMapping("/{numeroDocumento}")
    @Operation(summary = "Obtiene una persona por DNI")
    public ResponseEntity<PersonaDTO> obtenerPersona(@PathVariable String numeroDocumento) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(personaService.obtenerPersonaIn(numeroDocumento).get());

    }

    @GetMapping()
    @Operation(summary = "Obtiene todas las personas activas")
    public ResponseEntity<List<PersonaDTO>> obtenerPersonasActivas() {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(personaService.obtenerPersonasActivasIn());

    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar una persona")
    public ResponseEntity<PersonaDTO> actualizarPersona(@PathVariable Long id, @RequestBody InformacionRequest informacionRequest) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(personaService.actualizarPersonaIn(id, informacionRequest));

    }

    @DeleteMapping("/{idPersona}")
    @Operation(summary = "Eliminar una persona")
    public ResponseEntity<PersonaDTO> eliminarPersona(@PathVariable Long idPersona) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(personaService.eliminarPersonaIn(idPersona));

    }
}
