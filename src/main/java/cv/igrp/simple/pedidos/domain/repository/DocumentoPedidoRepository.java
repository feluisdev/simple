package cv.igrp.simple.pedidos.domain.repository;

import cv.igrp.simple.pedidos.domain.models.Documento;
import cv.igrp.simple.shared.domain.valueobject.Identificador;

import java.util.List;
import java.util.Optional;

public interface DocumentoPedidoRepository {

    Documento save(Documento documento);

    Optional<Documento> findById(Identificador documentoUuid);

    Optional<Documento> findByPedidoIdAndDocumentoId(Identificador pedidoId ,Identificador documentoUuid);

    List<Documento> findDocumentosByPedido(Identificador pedidoId);
}
