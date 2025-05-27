package cv.igrp.simple.pedido.domain.repository;

import cv.igrp.simple.pedido.domain.models.CategoriaPedido;

public interface CategoriaPedidoRepository {

    void save(CategoriaPedido categoria);

    CategoriaPedido getById(Integer categoriaId);
}
