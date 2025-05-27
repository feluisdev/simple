package cv.igrp.simple.pedido.infrastructure.mappers;

import cv.igrp.simple.pedido.domain.models.StatusPedido;
import cv.igrp.simple.pedido.infrastructure.persistence.entity.StatusPedidoEntity;
import org.springframework.stereotype.Component;

@Component
public class StatusPedidoMapper {

    public StatusPedidoEntity toEntity(StatusPedido domain) {
        StatusPedidoEntity entity = new StatusPedidoEntity();
        entity.setCodigo(domain.getCodigo());
        entity.setNome(domain.getNome());
        entity.setDescricao(domain.getDescricao());
        entity.setCor(domain.getCor());
        entity.setIcone(domain.getIcone());
        entity.setOrdem(domain.getOrdem());
        entity.setVisivelPortal(domain.isVisivelPortal());
        return entity;
    }

    public StatusPedido toDomain(StatusPedidoEntity entity) {
        return new StatusPedido(
                entity.getCodigo(),
                entity.getNome(),
                entity.getDescricao(),
                entity.getCor(),
                entity.getIcone(),
                entity.getOrdem(),
                entity.isVisivelPortal()
        );
    }
}
