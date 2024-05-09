package org.quarkusclub.repositories;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.RestClientBuilder;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.quarkusclub.dtos.ClienteStatusResponse;
import org.quarkusclub.restclients.ConveniadosService;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.jboss.logging.Logger;
@ApplicationScoped
public class ConveniosRepository {

    private static final Logger log = Logger.getLogger(ConveniosRepository.class);
    private final Map<String, ConveniadosService> convenios = new HashMap<>();

    private ConveniosRepository() {
        convenios.put("boa vida", RestClientBuilder.newBuilder()
                .baseUri(URI.create("http://localhost:8081/"))
                .build(ConveniadosService.class));

        convenios.put("vida longa", RestClientBuilder.newBuilder()
                .baseUri(URI.create("http://localhost:8082/"))
                .build(ConveniadosService.class));
    }

    public ClienteStatusResponse getStatusConveniado(UUID idConveniado, String plano) {
        ConveniadosService conveniadosService = this.convenios.get(plano);
        log.infof("ConveniadoService: %s", plano);
        return conveniadosService.getStatusConveniado(idConveniado);
    }
}
