package org.quarkusclub.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.quarkusclub.dtos.ClienteDTO;
import org.quarkusclub.models.ClienteEntity;
import org.quarkusclub.models.exceptions.ClienteNaoCadastradoException;
import org.quarkusclub.repositories.CadastroClienteRepository;
import org.quarkusclub.utils.mappers.ClienteMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class CadastroClienteService implements CadastroClienteServiceInterface {

    @Inject
    private CadastroClienteRepository cadastroClienteRepository;

    @Override
    public ClienteDTO createCliente(ClienteDTO cliente) {
        ClienteEntity newCliente = ClienteMapper.mapDtoToEntity(cliente);
        cadastroClienteRepository.createCliente(newCliente);
        return ClienteMapper.mapEntityToDto(newCliente);
    }

    @Override
    public ClienteDTO updateAllCliente(UUID idcliente, ClienteDTO cliente) throws ClienteNaoCadastradoException {

        ClienteEntity clienteEntity = cadastroClienteRepository.consultaCliente(idcliente);
        if(clienteEntity != null){
            cadastroClienteRepository.updateCliente(ClienteMapper.mapDtoToEntity(cliente));
            return cliente;
        } else {
            throw new ClienteNaoCadastradoException("Cliente não encontrado");
        }

    }

    @Override
    public ClienteDTO updateParcialCliente(UUID idcliente, ClienteDTO cliente) throws ClienteNaoCadastradoException {
        ClienteEntity clienteEntity = cadastroClienteRepository.consultaCliente(idcliente);

        if (clienteEntity == null) {
            throw new ClienteNaoCadastradoException("Cliente não encontrado");
        }

        if (cliente.nome() != null) {
            clienteEntity.setNome(cliente.nome());
        }
        if (cliente.cpf() != null) {
            clienteEntity.setCpf(cliente.cpf());
        }
        if (cliente.endereco() != null) {
            clienteEntity.setEndereco(cliente.endereco());
        }
        if (cliente.cidade() != null) {
            clienteEntity.setCidade(cliente.cidade());
        }
        if (cliente.estado() != null) {
            clienteEntity.setEstado(cliente.estado());
        }
        if (cliente.email() != null) {
            clienteEntity.setEmail(cliente.email());
        }
        if (cliente.telefone() != null) {
            clienteEntity.setTelefone(cliente.telefone());
        }
        if (cliente.nomePlano() != null) {
            clienteEntity.setNomePlano(cliente.nomePlano());
        }
        if (cliente.indicacao() != null) {
            clienteEntity.setIndicacao(cliente.indicacao());
        }

        cadastroClienteRepository.updateCliente(clienteEntity);

        return ClienteMapper.mapEntityToDto(clienteEntity);

    }

    @Override
    public List<ClienteDTO> consultaClientes() {
        List<ClienteEntity> clientes = cadastroClienteRepository.consultaClientes();
        if (clientes == null) {
            throw new RuntimeException("Nenhum cliente encontrado.");
        }
        return ClienteMapper.mapEntityListToDtoList(clientes);
    }

    @Override
    public ClienteDTO consultaCliente(UUID idcliente) throws ClienteNaoCadastradoException {
        ClienteEntity clienteEntity = cadastroClienteRepository.consultaCliente(idcliente);
        if (clienteEntity != null) {
            return ClienteMapper.mapEntityToDto(clienteEntity);
        }
        throw new ClienteNaoCadastradoException("Cliente não encontrado");
    }
}
