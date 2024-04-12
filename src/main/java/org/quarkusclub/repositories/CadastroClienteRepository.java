package org.quarkusclub.repositories;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.quarkusclub.dtos.ClienteDTO;
import org.quarkusclub.models.ClienteEntity;
import org.quarkusclub.models.exceptions.ClienteNaoCadastradoException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class CadastroClienteRepository {

    @Inject
    EntityManager entityManager;

    @Transactional
    public ClienteEntity createCliente(ClienteEntity cliente){
        cliente.setId(UUID.randomUUID());
        cliente.persist();
        return cliente;
    }

    @Transactional
    public ClienteEntity updateCliente(ClienteEntity cliente) {
        ClienteEntity clienteEntity = ClienteEntity.findById(cliente.getId());
        if(clienteEntity == null) return null;
        entityManager.merge(cliente);
        return cliente;
    }

    public List<ClienteEntity> consultaClientes(){
        return ClienteEntity.listAll();
    }

    public ClienteEntity consultaCliente(UUID idcliente) {
        return ClienteEntity.findById(idcliente);
    }
}
