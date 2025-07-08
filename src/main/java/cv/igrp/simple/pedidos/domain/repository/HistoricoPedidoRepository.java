package cv.igrp.simple.pedidos.domain.repository;

import cv.igrp.simple.pedidos.domain.models.HistoricoPedido;
import cv.igrp.simple.shared.domain.valueobject.Identificador;

import java.util.List;
import java.util.Optional;

public interface HistoricoPedidoRepository {

    Optional<HistoricoPedido> findById(Identificador historicoPedidoUuid);

    Optional<HistoricoPedido> findByPedidoIdAndHistoricoId(Identificador pedidoId ,Identificador historicoPedidoUuid);

    List<HistoricoPedido> findHistoricosByPedido(Identificador pedidoId);
}
