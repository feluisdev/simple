package cv.igrp.simple.pedidos.infrastructure.mappers;

import cv.igrp.simple.configuracoes.infrastructure.mappers.StatusPedidoMapper;
import cv.igrp.simple.pedidos.application.dto.HistoricoPedidoResponseDTO;
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


    public  HistoricoPedidoEntity toEntity(HistoricoPedido historico, PedidoEntity pedidoEntity) {
        HistoricoPedidoEntity entity = new HistoricoPedidoEntity();
        entity.setId(historico.getIdDb());
        entity.setHistoricoUuid(historico.getHistoricoUuid().getValor());
        entity.setUserId(historico.getUserId());
        entity.setDataRegistro(historico.getDataRegistro());
        entity.setObservacao(historico.getObservacao());
        entity.setPedidoId(pedidoEntity);
        entity.setStatusId(statusPedidoMapper.toEntity(historico.getStatus()));

        return entity;
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

    public HistoricoPedidoResponseDTO toResponseDTO(HistoricoPedido historicoPedido) {
        if (historicoPedido == null) {
            return null;
        }

        return new HistoricoPedidoResponseDTO(
                historicoPedido.getHistoricoUuid().getStringValor(),
                historicoPedido.getPedido().getPedidoUuid().getStringValor(),
                historicoPedido.getStatus().getStatusPedidoUuid().getStringValor(),
                historicoPedido.getStatus().getNome(),
                historicoPedido.getStatus().getCor(),
                null, // Preencher se necessário
                null, // Preencher se necessário
                null, // Preencher se necessário
                null, // Preencher se necessário
                historicoPedido.getDataRegistro(),
                historicoPedido.getObservacao()
        );
    }
}
