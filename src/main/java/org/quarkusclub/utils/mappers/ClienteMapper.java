package org.quarkusclub.utils.mappers;

import org.quarkusclub.dtos.ClienteDTO;
import org.quarkusclub.models.ClienteEntity;

import java.util.List;

public class ClienteMapper {
    public static ClienteEntity mapDtoToEntity(ClienteDTO dto) {
        ClienteEntity entity = new ClienteEntity();
        entity.setId(dto.id());
        entity.setNome(dto.nome());
        entity.setCpf(dto.cpf());
        entity.setEmail(dto.email());
        entity.setTelefone(dto.telefone());
        entity.setEndereco(dto.endereco());
        entity.setCidade(dto.cidade());
        entity.setEstado(dto.estado());
        entity.setNomePlano(dto.nomePlano());
        entity.setIndicacao(dto.indicacao());
        entity.setIdConvenio(dto.idConvenio());
        entity.setIdConveniado(dto.idConveniado());
        return entity;
    }

    public static ClienteDTO mapEntityToDto(ClienteEntity entity) {
        return new ClienteDTO(
                entity.getId(),
                entity.getNome(),
                entity.getCpf(),
                entity.getEmail(),
                entity.getTelefone(),
                entity.getEndereco(),
                entity.getCidade(),
                entity.getEstado(),
                entity.getNomePlano(),
                entity.getIndicacao(),
                entity.getIdConvenio(),
                entity.getIdConveniado()
        );
    }

    public static List<ClienteDTO> mapEntityListToDtoList(List<ClienteEntity> entities) {
        return entities.stream().map(ClienteMapper::mapEntityToDto).toList();
    }
}