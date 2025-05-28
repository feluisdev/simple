package cv.igrp.simple.pedido.infrastructure.persistence.repository;

import cv.igrp.simple.pedido.domain.models.TipoPedido;
import cv.igrp.simple.pedido.domain.repository.TipoPedidoRepository;
import cv.igrp.simple.pedido.infrastructure.mappers.TipoPedidoMapper;
import org.springframework.stereotype.Repository;

@Repository
public class TipoPedidoRepositorioImpl implements TipoPedidoRepository {

    private final TipoPedidoEntityRepository jpTipoPedidoEntityRepository;

    private final TipoPedidoMapper tipoPedidoMapper;

    public TipoPedidoRepositorioImpl(TipoPedidoEntityRepository jpTipoPedidoEntityRepository, TipoPedidoMapper tipoPedidoMapper) {
        this.jpTipoPedidoEntityRepository = jpTipoPedidoEntityRepository;
        this.tipoPedidoMapper = tipoPedidoMapper;
    }

    @Override
    public void save(TipoPedido tipoPedido) {

        var entity = tipoPedidoMapper.toEntity(tipoPedido);
        jpTipoPedidoEntityRepository.save(entity);

    }

    @Override
    public TipoPedido getById(Integer id) {
        var entity = jpTipoPedidoEntityRepository.findById(id).orElseThrow();

        return tipoPedidoMapper.toDomain(entity);
    }
}
