package com.practica.avanzado.msbaldeonestacio.infraestructure.rest.client;

import com.practica.avanzado.msbaldeonestacio.domain.aggregates.response.ReniecResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "recienc-cleinte", url = "https://api.apis.net.pe/v2/reniec")
public interface ReniecCliente {

    @RequestMapping("/dni")
    ReniecResponse obtenerInformacion(@RequestParam("numero") String numeroDocumento,
                                      @RequestHeader("Authorization") String bearerToken);
}
