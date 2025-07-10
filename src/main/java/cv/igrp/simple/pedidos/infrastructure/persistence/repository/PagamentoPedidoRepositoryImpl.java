package cv.igrp.simple.pedidos.infrastructure.persistence.repository;

import cv.igrp.simple.pedidos.domain.models.Pagamento;
import cv.igrp.simple.pedidos.domain.repository.PagamentoPedidoRepository;
import cv.igrp.simple.pedidos.infrastructure.mappers.PagamentoPedidoMapper;
import cv.igrp.simple.pedidos.infrastructure.mappers.PedidoMapper;
import cv.igrp.simple.shared.domain.valueobject.Identificador;
import cv.igrp.simple.shared.infrastructure.persistence.repository.PagamentoPedidoEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PagamentoPedidoRepositoryImpl implements PagamentoPedidoRepository {

    private final PagamentoPedidoEntityRepository pagamentoPedidoJpaRepository;
    private final PagamentoPedidoMapper pagamentoPedidoMapper;
    private final PedidoMapper pedidoMapper;


    @Override
    public Optional<Pagamento> findById(Identificador pagamentoUuid) {
        if (pagamentoUuid == null || pagamentoUuid.getValor() == null) {
            return Optional.empty();
        }
        return pagamentoPedidoJpaRepository
                .findByPagamentoUuid(pagamentoUuid.getValor())
                .map(entity -> {
                    var pedido = pedidoMapper.toLightDomain(entity.getPedidoId());
                    return pagamentoPedidoMapper.toDomainWithPedido(entity, pedido);
                });
    }

    @Override
    public Optional<Pagamento> findByPedidoIdAndPagamentoId(Identificador pedidoId, Identificador pagamentoUuid) {
        if (pagamentoUuid == null || pagamentoUuid.getValor() == null) {
            return Optional.empty();
        }
        return pagamentoPedidoJpaRepository
                .findByPedidoId_PedidoUuidAndPagamentoUuid(pedidoId.getValor(), pagamentoUuid.getValor())
                .map(entity -> {
                    var pedido = pedidoMapper.toLightDomain(entity.getPedidoId());
                    return pagamentoPedidoMapper.toDomainWithPedido(entity, pedido);
                });
    }

}
