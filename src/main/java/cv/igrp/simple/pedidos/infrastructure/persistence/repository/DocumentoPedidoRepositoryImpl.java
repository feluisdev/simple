package cv.igrp.simple.pedidos.infrastructure.persistence.repository;

import cv.igrp.simple.pedidos.domain.models.Documento;
import cv.igrp.simple.pedidos.domain.repository.DocumentoPedidoRepository;
import cv.igrp.simple.pedidos.infrastructure.mappers.DocumentoPedidoMapper;
import cv.igrp.simple.pedidos.infrastructure.mappers.PedidoMapper;
import cv.igrp.simple.shared.domain.valueobject.Identificador;
import cv.igrp.simple.shared.infrastructure.persistence.repository.DocumentoPedidoEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class DocumentoPedidoRepositoryImpl implements DocumentoPedidoRepository {

    private final DocumentoPedidoEntityRepository documentoPedidoEntityRepository;
    private final DocumentoPedidoMapper documentoPedidoMapper;
    private final PedidoMapper pedidoMapper;

    @Override
    public Optional<Documento> findById(Identificador documentoUuid) {
        if (documentoUuid == null || documentoUuid.getValor() == null) {
            return Optional.empty();
        }
        return documentoPedidoEntityRepository.findByDocumentoUuid(documentoUuid.getValor())
                .map(entity -> {
                    var pedido = pedidoMapper.toLightDomain(entity.getPedidoId());
                    return documentoPedidoMapper.toDomainWithPedido(entity, pedido);
                });
    }

    @Override
    public Optional<Documento> findByPedidoIdAndDocumentoId(Identificador pedidoId, Identificador documentoUuid) {
        if (documentoUuid == null || documentoUuid.getValor() == null) {
            return Optional.empty();
        }
        return documentoPedidoEntityRepository
                .findByPedidoId_PedidoUuidAndDocumentoUuid(pedidoId.getValor(), documentoUuid.getValor())
                .map(entity -> {
                    var pedido = pedidoMapper.toLightDomain(entity.getPedidoId());
                    return documentoPedidoMapper.toDomainWithPedido(entity, pedido);
                });
    }

    @Override
    public List<Documento> findDocumentosByPedido(Identificador pedidoId) {
        return documentoPedidoEntityRepository.findByPedidoId_PedidoUuid(pedidoId.getValor())
                .stream()
                .map(entity -> {
                    var pedido = pedidoMapper.toLightDomain(entity.getPedidoId());
                    return documentoPedidoMapper.toDomainWithPedido(entity, pedido);
                })
                .toList();
    }
}
