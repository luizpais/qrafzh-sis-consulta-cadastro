package org.quarkusclub;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.reactive.RestResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Path("/v1/clientes")
public class CadastroClienteResource {



    @Inject
    private CadastroClienteService cadastroClienteService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public RestResponse<List<ClienteDTO>> consultaClientes() {
        List<ClienteDTO> clientes = cadastroClienteService.consultaClientes();
        return RestResponse.status(Response.Status.OK,clientes);
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
        ClienteDTO newCliente = cadastroClienteService.createCliente(cliente);

        return RestResponse.status(Response.Status.CREATED, newCliente);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public RestResponse<ClienteDTO> updateCliente(ClienteDTO cliente) {
        if(cliente == null) {
            ClienteDTO responseClient = new ClienteDTO();
            responseClient.setResponseMessage("Cliente não informado");
            return RestResponse.status(RestResponse.Status.BAD_REQUEST, responseClient);
        }
        if(cliente.getId() == null) {
            ClienteDTO responseClient = new ClienteDTO();
            responseClient.setResponseMessage("Id do cliente não informado");
            return RestResponse.status(RestResponse.Status.BAD_REQUEST, responseClient);
        }
        ClienteDTO updatedCliente = cadastroClienteService.updateAllCliente(cliente);
        if(updatedCliente == null) {
            ClienteDTO responseClient = new ClienteDTO();
            responseClient.setResponseMessage("Cliente não encontrado");
            return RestResponse.status(RestResponse.Status.NOT_FOUND, responseClient);
        }

        return RestResponse.status(RestResponse.Status.OK, updatedCliente);
    }

    @PATCH
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{idcliente}")
    public RestResponse<ClienteDTO> updateParcialCliente(@PathParam("idcliente") UUID idcliente, ClienteDTO cliente) {
        if(cliente == null) {
            ClienteDTO responseClient = new ClienteDTO();
            responseClient.setResponseMessage("Cliente não informado");
            return RestResponse.status(RestResponse.Status.BAD_REQUEST, responseClient);
        }
        if(idcliente == null) {
            ClienteDTO responseClient = new ClienteDTO();
            responseClient.setResponseMessage("Id do cliente não informado");
            return RestResponse.status(RestResponse.Status.BAD_REQUEST, responseClient);
        }
        ClienteDTO updatedCliente = cadastroClienteService.updateParcialCliente(idcliente, cliente);
        if(updatedCliente == null) {
            ClienteDTO responseClient = new ClienteDTO();
            responseClient.setResponseMessage("Cliente não encontrado");
            return RestResponse.status(RestResponse.Status.NOT_FOUND, responseClient);
        }

        return RestResponse.status(RestResponse.Status.OK, updatedCliente);
    }

}
