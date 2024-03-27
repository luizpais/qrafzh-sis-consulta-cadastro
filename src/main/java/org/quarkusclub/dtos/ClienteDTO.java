package org.quarkusclub.dtos;

import lombok.Data;
import lombok.Setter;

import java.util.UUID;

@Data
public class ClienteDTO {
    @Setter
    private UUID id;
    private String nome;
    private String cpf;
    private String email;
    private String telefone;
    private String endereco;
    private String cidade;
    private String estado;
    private String nomePlano;
    private String indicacao;
}
