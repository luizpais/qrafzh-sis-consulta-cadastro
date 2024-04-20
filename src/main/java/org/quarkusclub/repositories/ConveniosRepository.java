package org.quarkusclub.repositories;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.quarkusclub.dtos.ClienteStatusResponse;
import org.quarkusclub.restclients.ConveniadosService;

import java.util.UUID;

@ApplicationScoped
public class ConveniosRepository {

    @RestClient
    ConveniadosService conveniadosService;

    public ClienteStatusResponse getStatusConveniado(UUID idConveniado) {
        return conveniadosService.getStatusConveniado(idConveniado);
    }
}
