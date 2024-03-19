package org.quarkusclub;

import lombok.Data;
import lombok.Setter;

import java.util.UUID;

@Data
public class ClienteDTO {
    @Setter
    private UUID id;
    @Setter
    private String responseMessage;
    private String nome;
    private String email;
    private String telefone;
    private String endereco;
    private String cidade;
    private String estado;
    private String nomePlano;
    private String indicacao;
}
