package cv.igrp.simple.pedidos.domain.repository;

import cv.igrp.simple.pedidos.domain.models.HistoricoPedido;
import cv.igrp.simple.pedidos.domain.models.Pagamento;
import cv.igrp.simple.shared.domain.valueobject.Identificador;

import java.util.List;
import java.util.Optional;

public interface PagamentoPedidoRepository {

    Optional<Pagamento> findById(Identificador pagamentoUuid);

    Optional<Pagamento> findByPedidoIdAndPagamentoId(Identificador pedidoId ,Identificador pagamentoUuid);

    List<Pagamento> findPagamentosByPedido(Identificador pedidoId);
}
