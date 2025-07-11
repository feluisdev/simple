package cv.igrp.simple.pedidos.domain.repository;

import cv.igrp.simple.pedidos.domain.models.Avaliacao;
import cv.igrp.simple.shared.domain.valueobject.Identificador;

import java.util.List;
import java.util.Optional;

public interface AvaliacaoRepository {

    Avaliacao save(Avaliacao avaliacao);

    Optional<Avaliacao> findById(Identificador avaliacaoUuid);

    Optional<Avaliacao> findByPedidoIdAndAvaliacaoID(Identificador pedidoId ,Identificador avaliacaoUuid);

    Optional<Avaliacao> findAvaliacaoByPedido(Identificador pedidoId);

    List<Avaliacao> findAvaliacoesByPedido(Identificador pedidoId);

}
