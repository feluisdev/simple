package cv.igrp.simple.pedidos.domain.repository;

import cv.igrp.simple.pedidos.domain.models.Pagamento;
import cv.igrp.simple.shared.domain.valueobject.Identificador;

import java.util.Optional;

public interface PagamentoPedidoRepository {

    Pagamento save(Pagamento pagamento);

    Optional<Pagamento> findById(Identificador pagamentoUuid);

    Optional<Pagamento> findByPedidoIdAndPagamentoId(Identificador pedidoId ,Identificador pagamentoUuid);

}
