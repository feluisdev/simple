package cv.igrp.simple.configuracoes.infrastructure.mappers;

import cv.igrp.simple.configuracoes.application.dto.StatusPedidoResponseDTO;
import cv.igrp.simple.configuracoes.domain.models.StatusPedido;
import cv.igrp.simple.shared.domain.valueobject.Identificador;
import cv.igrp.simple.shared.infrastructure.persistence.entity.StatusPedidoEntity;
import org.springframework.stereotype.Component;

@Component
public class StatusPedidoMapper {

    public StatusPedidoEntity toEntity(StatusPedido domain) {
        if (domain == null) {
            return null;
        }
        StatusPedidoEntity entity = new StatusPedidoEntity();
        if (domain.getNome() != null)
            entity.setId(domain.getId());

        entity.setStatusUuid(domain.getStatusPedidoUuid().getValor());
        entity.setNome(domain.getNome());
        entity.setCodigo(domain.getCodigo());
        entity.setDescricao(domain.getDescricao());
        entity.setCor(domain.getCor());
        entity.setIcone(domain.getIcone());
        entity.setFim(domain.isFim());
        entity.setAtivo(domain.isAtivo());
        entity.setOrdem(domain.getOrdem());
        entity.setVisivelPortal(domain.isVisivelPortal());


        return entity;
    }

    public StatusPedido toDomain(StatusPedidoEntity entity) {
        if (entity == null) {
            return null;
        }
        return StatusPedido.reconstruir(
                entity.getId(),
                Identificador.from(entity.getStatusUuid()),
                entity.getNome(),
                entity.getCodigo(),
                entity.getDescricao(),
                entity.getCor(),
                entity.getIcone(),
                entity.isFim(),
                entity.isAtivo(),
                entity.isVisivelPortal(),
                entity.getOrdem()
        );
    }

    public StatusPedidoResponseDTO toStatusPedidoResponseDTO(StatusPedido domain) {
        if (domain == null) {
            return null;
        }

        StatusPedidoResponseDTO dto = new StatusPedidoResponseDTO();
        dto.setId(domain.getId()); // ID sequencial
        dto.setCodigo(domain.getCodigo());
        dto.setNome(domain.getNome());
        dto.setDescricao(domain.getDescricao());
        dto.setCor(domain.getCor());
        dto.setIcone(domain.getIcone());
        dto.setFim(domain.isFim());
        dto.setAtivo(domain.isAtivo());
        dto.setVisivelPortal(domain.isVisivelPortal());
        dto.setStatusPedidoId(domain.getStatusPedidoUuid().getValor().toString());
        dto.setOrdem(domain.getOrdem());
        return dto;
    }

}



