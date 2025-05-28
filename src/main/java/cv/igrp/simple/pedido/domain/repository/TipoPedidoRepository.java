package cv.igrp.simple.pedido.domain.repository;

import cv.igrp.simple.pedido.domain.models.TipoPedido;

public interface TipoPedidoRepository {

    void save(TipoPedido tipoPedido);


    TipoPedido getById(Integer id);
}
