package cv.igrp.simple.pedidos.infrastructure.persistence.repository;

import cv.igrp.simple.pedidos.domain.models.Avaliacao;
import cv.igrp.simple.pedidos.domain.repository.AvaliacaoRepository;
import cv.igrp.simple.pedidos.infrastructure.mappers.AvaliacaoMapper;
import cv.igrp.simple.shared.domain.valueobject.Identificador;
import cv.igrp.simple.shared.infrastructure.persistence.repository.AvaliacaoPedidoEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AvaliacaoPedidoRepositoryImpl implements AvaliacaoRepository {

    private final AvaliacaoPedidoEntityRepository avaliacaoPedidoEntityRepository;
    private final AvaliacaoMapper avaliacaoMapper;

    @Override
    public Optional<Avaliacao> findById(Identificador avaliacaoUuid) {
        if (avaliacaoUuid == null || avaliacaoUuid.getValor() == null) {
            return Optional.empty();
        }

        return avaliacaoPedidoEntityRepository
                .findByAvaliacaoUuid(avaliacaoUuid.getValor())
                .map(avaliacaoMapper::toDomain);
    }

    @Override
    public Optional<Avaliacao> findByPedidoIdAndAvaliacaoID(Identificador pedidoUuid, Identificador avaliacaoUuid) {
        if (avaliacaoUuid == null || avaliacaoUuid.getValor() == null) {
            return Optional.empty();
        }

        return avaliacaoPedidoEntityRepository
                .findByPedidoId_PedidoUuidAndAvaliacaoUuid(pedidoUuid.getValor(),avaliacaoUuid.getValor())
                .map(avaliacaoMapper::toDomain);
    }

    @Override
    public Optional<Avaliacao> findAvaliacaoByPedido(Identificador pedidoId) {
        return Optional.empty();
    }

    @Override
    public List<Avaliacao> findAvaliacoesByPedido(Identificador pedidoId) {
        return avaliacaoPedidoEntityRepository
                .findByPedidoId_PedidoUuid(pedidoId.getValor())
                .stream()
                .map(avaliacaoMapper::toDomain)
                .toList();
    }
}
