package cv.igrp.simple.pedidos.domain.repository;

import cv.igrp.simple.pedidos.domain.models.Pedido;
import cv.igrp.simple.pedidos.domain.filter.PedidoFilter;
import cv.igrp.simple.pedidos.domain.valueobject.CodigoAcompanhamento;
import cv.igrp.simple.shared.domain.valueobject.Identificador;

import java.util.List;
import java.util.Optional;

public interface PedidoRepository {

    // Save or update an order
    Pedido save(Pedido pedido);

    // Find an order by its UUID
    Optional<Pedido> findById(Identificador pedidoUuid);

    // Find an order by its tracking code
    Optional<Pedido> findByCodigoAcompanhamento(CodigoAcompanhamento codigoAcompanhamento);

    List<Pedido> findAll(PedidoFilter filter);

    List<Pedido> findAllByUtenteId(Integer utenteID);

}
