package org.quarkusclub;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.jboss.resteasy.reactive.RestResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Path("/v1/clientes")
public class CadastroClienteResource {

    List<ClienteDTO> clientes = new ArrayList<>();

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello from RESTEasy Reactive";
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public RestResponse<ClienteDTO> createCliente(ClienteDTO cliente) {
        if(cliente == null) {
            ClienteDTO responseClient = new ClienteDTO();
            responseClient.setResponseMessage("Cliente não informado");
            return RestResponse.status(RestResponse.Status.BAD_REQUEST, responseClient);
        }
        cliente.setId(UUID.randomUUID());
        clientes.add(cliente);
        return RestResponse.ok(cliente);
    }



}
