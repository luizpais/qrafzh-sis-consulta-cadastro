package org.quarkusclub;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.reactive.RestResponse;


import java.util.List;
import java.util.UUID;
import org.jboss.logging.Logger;

@Path("/v1/clientes")
public class CadastroClienteResource {


    @Inject
    private CadastroClienteServiceInterface cadastroClienteService;

    @Inject
    Logger log;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public RestResponse<List<ClienteDTO>> consultaClientes() {
        try {
            List<ClienteDTO> clientes = cadastroClienteService.consultaClientes();
            return RestResponse.status(Response.Status.OK,clientes);
        } catch (RuntimeException e) {
            log.info("Erro ao consultar clientes", e);
            ClienteDTO clienteDTO = new ClienteDTO();
            clienteDTO.setResponseMessage(e.getMessage());
            return RestResponse.status(Response.Status.INTERNAL_SERVER_ERROR, List.of(clienteDTO));
        }
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

        try {
            ClienteDTO newCliente = cadastroClienteService.createCliente(cliente);
            return RestResponse.status(Response.Status.CREATED, newCliente);
        }catch (Exception e) {
            log.info("Erro ao consultar clientes", e);
            ClienteDTO clienteDTO = new ClienteDTO();
            clienteDTO.setResponseMessage(e.getMessage());
            return RestResponse.status(Response.Status.INTERNAL_SERVER_ERROR, clienteDTO);
        }
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{idcliente}")
    public RestResponse<ClienteDTO> updateCliente(@PathParam("idcliente") UUID idcliente, ClienteDTO cliente) {
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
        try {
            ClienteDTO updatedCliente = cadastroClienteService.updateAllCliente(idcliente, cliente);
            return RestResponse.status(RestResponse.Status.OK, updatedCliente);
        } catch (ClienteNaoCadastradoException e) {
            log.info("Cliente não cadastrado!!!", e);
            ClienteDTO clienteDTO = new ClienteDTO();
            clienteDTO.setResponseMessage(e.getMessage());
            return RestResponse.status(Response.Status.NOT_FOUND, clienteDTO);
        } catch (RuntimeException e) {
            log.info("Erro ao consultar clientes", e);
            ClienteDTO clienteDTO = new ClienteDTO();
            clienteDTO.setResponseMessage(e.getMessage());
            return RestResponse.status(Response.Status.INTERNAL_SERVER_ERROR, clienteDTO);
        }
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
        try {
            ClienteDTO updatedCliente = cadastroClienteService.updateParcialCliente(idcliente, cliente);
            return RestResponse.status(RestResponse.Status.OK, updatedCliente);
        } catch (ClienteNaoCadastradoException e) {
            log.info("Cliente não cadastrado!!!", e);
            ClienteDTO clienteDTO = new ClienteDTO();
            clienteDTO.setResponseMessage(e.getMessage());
            return RestResponse.status(Response.Status.NOT_FOUND, clienteDTO);
        } catch (RuntimeException e) {
            log.info("Erro ao consultar clientes", e);
            ClienteDTO clienteDTO = new ClienteDTO();
            clienteDTO.setResponseMessage(e.getMessage());
            return RestResponse.status(Response.Status.INTERNAL_SERVER_ERROR, clienteDTO);
        }
    }

}
