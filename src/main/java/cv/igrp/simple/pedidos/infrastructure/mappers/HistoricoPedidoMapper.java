package cv.igrp.simple.pedidos.infrastructure.mappers;

import cv.igrp.simple.configuracoes.infrastructure.mappers.StatusPedidoMapper;
import cv.igrp.simple.pedidos.domain.models.HistoricoPedido;
import cv.igrp.simple.pedidos.domain.models.Pedido;
import cv.igrp.simple.shared.domain.valueobject.Identificador;
import cv.igrp.simple.shared.infrastructure.persistence.entity.HistoricoPedidoEntity;
import cv.igrp.simple.shared.infrastructure.persistence.entity.PedidoEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class HistoricoPedidoMapper {


    private final StatusPedidoMapper statusPedidoMapper;

    public  HistoricoPedidoEntity toEntity(HistoricoPedido historico) {
        HistoricoPedidoEntity entity = new HistoricoPedidoEntity();
        entity.setId(historico.getIdDb());
        entity.setHistoricoUuid(historico.getHistoricoUuid().getStringValor());
        entity.setUserId(historico.getUserId());
        entity.setDataRegistro(historico.getDataRegistro());
        entity.setObservacao(historico.getObservacao());
        entity.setPedidoId(null);
        entity.setStatusId(statusPedidoMapper.toEntity(historico.getStatus()));

        return entity;
    }

    public  HistoricoPedidoEntity toEntity(HistoricoPedido historico, PedidoEntity pedidoEntity) {
        HistoricoPedidoEntity entity = new HistoricoPedidoEntity();
        entity.setId(historico.getIdDb());
        entity.setHistoricoUuid(historico.getHistoricoUuid().getStringValor());
        entity.setUserId(historico.getUserId());
        entity.setDataRegistro(historico.getDataRegistro());
        entity.setObservacao(historico.getObservacao());
        entity.setPedidoId(pedidoEntity);
        entity.setStatusId(statusPedidoMapper.toEntity(historico.getStatus()));

        return entity;
    }

    public  HistoricoPedido toDomain(HistoricoPedidoEntity entity) {
        return HistoricoPedido.reconstruir(
                entity.getId(),
                Identificador.from(entity.getHistoricoUuid()),
                entity.getUserId(),
                entity.getDataRegistro(),
                entity.getObservacao(),
                null,
                statusPedidoMapper.toDomain(entity.getStatusId())
        );
    }

    public HistoricoPedido toDomainWithPedido(HistoricoPedidoEntity entity, Pedido pedido) {
        return HistoricoPedido.reconstruir(
                entity.getId(),
                Identificador.from(entity.getHistoricoUuid()),
                entity.getUserId(),
                entity.getDataRegistro(),
                entity.getObservacao(),
                pedido,
                statusPedidoMapper.toDomain(entity.getStatusId())
        );
    }
}
