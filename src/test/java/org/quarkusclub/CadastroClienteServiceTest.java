package org.quarkusclub;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.quarkusclub.dtos.ClienteDTO;
import org.quarkusclub.models.ClienteEntity;
import org.quarkusclub.models.exceptions.ClienteNaoCadastradoException;
import org.quarkusclub.repositories.CadastroClienteRepository;
import org.quarkusclub.services.CadastroClienteService;


import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.*;

class CadastroClienteServiceTest {
    @Mock
    List<ClienteDTO> clientes;

    @InjectMocks
    CadastroClienteService cadastroClienteService;

    @Mock
    CadastroClienteRepository cadastroClienteRepository;
    ClienteDTO clienteDTO = new ClienteDTO(
            UUID.fromString("1ae0de65-9e0e-4476-9f8c-a41123f9ca3c"),
            "nome",
            "cpf",
            "email",
            "telefone",
            "endereco",
            "cidade",
            "estado",
            "nomePlano",
            "indicacao",
            UUID.fromString("1ae0de65-9e0e-4476-9f8c-a41123f9ca3c"),
            UUID.fromString("1ae0de65-9e0e-4476-9f8c-a41123f9ca3c")

    );

    ClienteEntity clienteEntity = clienteEntity = new ClienteEntity(
            UUID.fromString("1ae0de65-9e0e-4476-9f8c-a41123f9ca3c"),
            "nome",
            "cpf",
            "email",
            "telefone",
            "endereco",
            "cidade",
            "estado",
            "nomePlano",
            "jose",
            UUID.fromString("1ae0de65-9e0e-4476-9f8c-a41123f9ca3c"),
            UUID.fromString("1ae0de65-9e0e-4476-9f8c-a41123f9ca3c")
    );

    UUID id = UUID.fromString("1ae0de65-9e0e-4476-9f8c-a41123f9ca3c");

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCreateCliente() {
        when(clientes.add(any())).thenReturn(true);

        ClienteDTO novoCliente = clienteDTO;
        ClienteDTO result = cadastroClienteService.createCliente(novoCliente);
        Assertions.assertTrue(CompareDtoAndEntity(result, clienteEntity));
    }

    @Test
    void testUpdateAllCliente() throws ClienteNaoCadastradoException {
        when(cadastroClienteRepository.consultaCliente(any())).thenReturn(clienteEntity);
        ClienteDTO result = cadastroClienteService.updateAllCliente(id, clienteDTO);
        Assertions.assertTrue(CompareDtoAndEntity(result, clienteEntity));
    }

    @Test
    void testUpdateParcialCliente() throws ClienteNaoCadastradoException {
        when(cadastroClienteRepository.consultaCliente(any())).thenReturn(clienteEntity);
        ClienteDTO result = cadastroClienteService.updateParcialCliente(id, clienteDTO);
        Assertions.assertTrue(CompareDtoAndEntity(result, clienteEntity));
    }

    @Test
    void testConsultaClientes() {
        when(cadastroClienteRepository.consultaClientes()).thenReturn(List.of(clienteEntity));
        List<ClienteDTO> result = cadastroClienteService.consultaClientes();
        Assertions.assertTrue(CompareDtoAndEntity(result.get(0), clienteEntity));
    }

    @Test
    void testConsultaCliente() throws ClienteNaoCadastradoException {
        when(cadastroClienteRepository.consultaCliente(any())).thenReturn(clienteEntity);
        ClienteDTO result = cadastroClienteService.consultaCliente(id);
        Assertions.assertTrue(CompareDtoAndEntity(result, clienteEntity));
    }

    boolean CompareDtoAndEntity(ClienteDTO dto, ClienteEntity entity) {
        return dto.id().equals(entity.getId()) &&
                dto.nome().equals(entity.getNome()) &&
                dto.cpf().equals(entity.getCpf()) &&
                dto.email().equals(entity.getEmail()) &&
                dto.telefone().equals(entity.getTelefone()) &&
                dto.endereco().equals(entity.getEndereco()) &&
                dto.cidade().equals(entity.getCidade()) &&
                dto.estado().equals(entity.getEstado()) &&
                dto.nomePlano().equals(entity.getNomePlano()) &&
                dto.indicacao().equals(entity.getIndicacao()) &&
                dto.idConvenio().equals(entity.getIdConvenio()) &&
                dto.idConveniado().equals(entity.getIdConveniado());
    }
}

//Generated with love by TestMe :) Please raise issues & feature requests at: https://weirddev.com/forum#!/testme