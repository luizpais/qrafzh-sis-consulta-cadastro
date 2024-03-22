package org.quarkusclub;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.reactive.RestResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class CadastroClienteService {

    //Nossa persistência em memória
    private List<ClienteDTO> clientes = new ArrayList<>();

    public ClienteDTO createCliente(ClienteDTO cliente) {
        cliente.setId(UUID.randomUUID());
        clientes.add(cliente);
        return cliente;
    }

    public ClienteDTO updateAllCliente(ClienteDTO cliente) {
        for (ClienteDTO c : clientes) {
            if(c.getId().equals(cliente.getId())) {
                c.setNome(cliente.getNome());
                c.setCpf(cliente.getCpf());
                c.setEndereco(cliente.getEndereco());
                c.setCidade(cliente.getCidade());
                c.setEstado(cliente.getEstado());
                c.setEmail(cliente.getEmail());
                c.setTelefone(cliente.getTelefone());
                c.setNomePlano(cliente.getNomePlano());
                c.setIndicacao(cliente.getIndicacao());
                return c;
            }
        }
        return null;
    }

    public ClienteDTO updateParcialCliente(UUID idcliente, ClienteDTO cliente) {
        for (ClienteDTO c : clientes) {
            if(c.getId().equals(idcliente)) {
                if(cliente.getNome() != null) {
                    c.setNome(cliente.getNome());
                }
                if(cliente.getCpf() != null) {
                    c.setCpf(cliente.getCpf());
                }
                if(cliente.getEndereco() != null) {
                    c.setEndereco(cliente.getEndereco());
                }
                if(cliente.getCidade() != null) {
                    c.setCidade(cliente.getCidade());
                }
                if(cliente.getEstado() != null) {
                    c.setEstado(cliente.getEstado());
                }
                if(cliente.getEmail() != null) {
                    c.setEmail(cliente.getEmail());
                }
                if(cliente.getTelefone() != null) {
                    c.setTelefone(cliente.getTelefone());
                }
                if(cliente.getNomePlano() != null) {
                    c.setNomePlano(cliente.getNomePlano());
                }
                if(cliente.getIndicacao() != null) {
                    c.setIndicacao(cliente.getIndicacao());
                }
                return c;
            }
        }
        return null;
    }

    public List<ClienteDTO> consultaClientes() {
        return clientes;
    }
}
