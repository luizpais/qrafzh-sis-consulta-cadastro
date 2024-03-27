package org.quarkusclub;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.quarkusclub.dtos.ClienteDTO;
import org.quarkusclub.models.exceptions.ClienteNaoCadastradoException;
import org.quarkusclub.services.CadastroClienteService;


import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.*;

class CadastroClienteServiceTest {
    @Mock
    List<ClienteDTO> clientes;

    @InjectMocks
    CadastroClienteService cadastroClienteService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCreateCliente() {
        when(clientes.add(any())).thenReturn(true);

        ClienteDTO novoCliente = new ClienteDTO();
        ClienteDTO result = cadastroClienteService.createCliente(novoCliente);
        Assertions.assertEquals(novoCliente, result);
    }

    @Test
    void testUpdateAllCliente() throws ClienteNaoCadastradoException {
        ClienteDTO result = cadastroClienteService.updateAllCliente(new UUID(0L, 0L), new ClienteDTO());
        Assertions.assertEquals(new ClienteDTO(), result);
    }

    @Test
    void testUpdateParcialCliente() throws ClienteNaoCadastradoException {
        ClienteDTO result = cadastroClienteService.updateParcialCliente(new UUID(0L, 0L), new ClienteDTO());
        Assertions.assertEquals(new ClienteDTO(), result);
    }

    @Test
    void testConsultaClientes() {
        List<ClienteDTO> result = cadastroClienteService.consultaClientes();
        Assertions.assertEquals(List.of(new ClienteDTO()), result);
    }
}

//Generated with love by TestMe :) Please raise issues & feature requests at: https://weirddev.com/forum#!/testme