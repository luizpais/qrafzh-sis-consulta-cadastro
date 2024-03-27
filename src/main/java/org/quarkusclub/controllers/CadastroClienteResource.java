package org.quarkusclub.controllers;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.reactive.RestResponse;


import java.util.List;
import java.util.UUID;

import org.jboss.logging.Logger;
import org.quarkusclub.models.exceptions.ClienteNaoCadastradoException;
import org.quarkusclub.dtos.ClienteDTO;
import org.quarkusclub.models.exceptions.ValidacaoDeDadosException;
import org.quarkusclub.services.CadastroClienteServiceInterface;

@Path("/v1/clientes")
public class CadastroClienteResource {


    @Inject
    private CadastroClienteServiceInterface cadastroClienteService;

    @Inject
    Logger log;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public RestResponse<List<ClienteDTO>> consultaClientes() {

        List<ClienteDTO> clientes = cadastroClienteService.consultaClientes();
        return RestResponse.status(Response.Status.OK, clientes);

    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public RestResponse<ClienteDTO> createCliente(ClienteDTO cliente) throws ValidacaoDeDadosException {
        if (cliente == null) {
            throw new ValidacaoDeDadosException("Cliente não informado", Response.Status.BAD_REQUEST);
        }

        ClienteDTO newCliente = cadastroClienteService.createCliente(cliente);
        return RestResponse.status(Response.Status.CREATED, newCliente);

    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{idcliente}")
    public RestResponse<ClienteDTO> updateCliente(@PathParam("idcliente") UUID idcliente, ClienteDTO cliente) throws ClienteNaoCadastradoException, ValidacaoDeDadosException {
        if (cliente == null) {
            throw new ValidacaoDeDadosException("Cliente não informado", Response.Status.BAD_REQUEST);
        }
        if (idcliente == null) {
            throw new ValidacaoDeDadosException("Id do cliente não informado", Response.Status.BAD_REQUEST);
        }
        ClienteDTO updatedCliente = cadastroClienteService.updateAllCliente(idcliente, cliente);
        return RestResponse.status(RestResponse.Status.OK, updatedCliente);
    }

    @PATCH
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{idcliente}")
    public RestResponse<ClienteDTO> updateParcialCliente(@PathParam("idcliente") UUID idcliente, ClienteDTO cliente) throws ClienteNaoCadastradoException, ValidacaoDeDadosException {
        if (cliente == null) {
            throw new ValidacaoDeDadosException("Cliente não informado", Response.Status.BAD_REQUEST);
        }
        if (idcliente == null) {
            throw new ValidacaoDeDadosException("Id do cliente não informado", Response.Status.BAD_REQUEST);
        }

        ClienteDTO updatedCliente = cadastroClienteService.updateParcialCliente(idcliente, cliente);
        return RestResponse.status(RestResponse.Status.OK, updatedCliente);


    }

}
