package org.quarkusclub.dtos;

import java.util.UUID;

public record ClienteDTO(
        UUID id,
        String nome,
        String cpf,
        String email,
        String telefone,
        String endereco,
        String cidade,
        String estado,
        String nomePlano,
        String indicacao,
        UUID idConvenio,
        UUID idConveniado
) {}