package org.quarkusclub.repositories;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Value;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.RestClientBuilder;
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

    @ConfigProperty( name = "convenio.boa-vida.url")
    private String boaVidaUrl;

    @ConfigProperty( name = "convenio.vida-longa.url")
    private String vidaLongaUrl;

    private ConveniosRepository() {
        convenios.put("boa vida", RestClientBuilder.newBuilder()
                .baseUri(URI.create(boaVidaUrl))
                .build(ConveniadosService.class));

        convenios.put("vida longa", RestClientBuilder.newBuilder()
                .baseUri(URI.create(vidaLongaUrl))
                .build(ConveniadosService.class));
    }

    public ClienteStatusResponse getStatusConveniado(UUID idConveniado, String plano) {
        ConveniadosService conveniadosService = this.convenios.get(plano);
        log.infof("ConveniadoService: %s", plano);
        return conveniadosService.getStatusConveniado(idConveniado);
    }
}
