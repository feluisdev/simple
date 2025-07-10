package cv.igrp.simple.pedidos.infrastructure.mappers;

import cv.igrp.simple.pedidos.application.dto.PagamentoPedidoResponseDTO;
import cv.igrp.simple.pedidos.domain.models.Pagamento;
import cv.igrp.simple.pedidos.domain.models.Pedido;
import cv.igrp.simple.shared.domain.valueobject.Identificador;
import cv.igrp.simple.shared.infrastructure.persistence.entity.PagamentoPedidoEntity;
import cv.igrp.simple.shared.infrastructure.persistence.entity.PedidoEntity;
import org.springframework.stereotype.Component;

@Component
public class PagamentoPedidoMapper {

    public PagamentoPedidoEntity toEntity(Pagamento pagamento, PedidoEntity pedidoEntity) {
        PagamentoPedidoEntity entity = new PagamentoPedidoEntity();
        entity.setId(pagamento.getIdDb());
        entity.setPagamentoUuid(pagamento.getPagamentoUuid().getValor());
        entity.setDataPagamento(pagamento.getDataPagamento());
        entity.setMetodoPagamento(pagamento.getMetodoPagamento());
        entity.setReferenciaPagamento(pagamento.getReferenciaPagamento());
        entity.setStatus(pagamento.getStatus());
        entity.setObservacao(pagamento.getObservacao());
        entity.setValor(pagamento.getValor());
        entity.setPedidoId(pedidoEntity);
        return entity;
    }

    public Pagamento toDomainWithPedido(PagamentoPedidoEntity entity, Pedido pedido) {
        return Pagamento.reconstruir(
                entity.getId(),
                Identificador.from(entity.getPagamentoUuid()),
                entity.getDataPagamento(),
                entity.getMetodoPagamento(),
                entity.getReferenciaPagamento(),
                entity.getStatus(),
                entity.getObservacao(),
                entity.getValor(),
                pedido
        );
    }

    public PagamentoPedidoResponseDTO toResponseDTO(Pagamento pagamento) {
        if (pagamento == null) {
            return null;
        }

        return new PagamentoPedidoResponseDTO(
                pagamento.getPagamentoUuid().getStringValor(),
                pagamento.getPedido().getPedidoUuid().getStringValor(),
                pagamento.getValor(),
                pagamento.getDataPagamento(),
                pagamento.getMetodoPagamento(),
                pagamento.getReferenciaPagamento(),
                pagamento.getStatus().getDescription(),
                pagamento.getObservacao()
        );
    }
}
