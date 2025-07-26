package cv.igrp.simple.pedidos.infrastructure.persistence.repository;

import cv.igrp.simple.pedidos.domain.models.HistoricoPedido;
import cv.igrp.simple.pedidos.domain.repository.HistoricoPedidoRepository;
import cv.igrp.simple.pedidos.infrastructure.mappers.HistoricoPedidoMapper;
import cv.igrp.simple.pedidos.infrastructure.mappers.PedidoMapper;
import cv.igrp.simple.shared.domain.valueobject.Identificador;
import cv.igrp.simple.shared.infrastructure.persistence.repository.HistoricoPedidoEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class HistoricoPedidoRepositoryImpl implements HistoricoPedidoRepository {

    private final HistoricoPedidoEntityRepository historicoPedidoJpaRepository;
    private final HistoricoPedidoMapper historicoPedidoMapper;
    private final PedidoMapper pedidoMapper;

    @Override
    public Optional<HistoricoPedido> findById(Identificador historicoPedidoUuid) {

        if (historicoPedidoUuid == null || historicoPedidoUuid.getValor() == null) {
            return Optional.empty();
        }
        return historicoPedidoJpaRepository
                .findByHistoricoUuid(historicoPedidoUuid.getValor())
                .map(entity -> {
                    var pedido = pedidoMapper.toLightDomain(entity.getPedidoId());
                    return historicoPedidoMapper.toDomainWithPedido(entity, pedido);
                });
    }

    @Override
    public Optional<HistoricoPedido> findByPedidoIdAndHistoricoId(Identificador pedidoId, Identificador historicoPedidoUuid) {
        if (historicoPedidoUuid == null || historicoPedidoUuid.getValor() == null) {
            return Optional.empty();
        }

        return historicoPedidoJpaRepository
                .findByPedidoId_PedidoUuidAndHistoricoUuid(pedidoId.getValor(), historicoPedidoUuid.getValor())
                .map(entity -> {
                    var pedido = pedidoMapper.toLightDomain(entity.getPedidoId());
                    return historicoPedidoMapper.toDomainWithPedido(entity, pedido);
                });
    }


    @Override
    public List<HistoricoPedido> findHistoricosByPedido(Identificador pedidoId) {
        return historicoPedidoJpaRepository.findByPedidoId_PedidoUuid(pedidoId.getValor())
                .stream()
                .map(entity -> {
                    var pedido = pedidoMapper.toLightDomain(entity.getPedidoId());
                    return historicoPedidoMapper.toDomainWithPedido(entity, pedido);
                })
                .toList();
    }
}
