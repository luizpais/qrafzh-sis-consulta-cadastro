package org.quarkusclub.restclients;


import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.quarkusclub.dtos.ClienteStatusResponse;

import java.util.UUID;

@Path("/conveniados")
@RegisterRestClient
public interface ConveniadosService {

    @GET
    @Path("/status/{idConveniado}")
    ClienteStatusResponse getStatusConveniado(@PathParam("idConveniado") UUID idConveniado);

}
