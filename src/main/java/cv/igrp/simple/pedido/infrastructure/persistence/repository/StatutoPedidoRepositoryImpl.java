package cv.igrp.simple.pedido.infrastructure.persistence.repository;

import cv.igrp.simple.pedido.domain.models.StatusPedido;
import cv.igrp.simple.pedido.domain.repository.StatusPedidoRepository;
import cv.igrp.simple.pedido.infrastructure.mappers.StatusPedidoMapper;
import org.springframework.stereotype.Repository;

@Repository
public class StatutoPedidoRepositoryImpl  implements StatusPedidoRepository {

    private final StatusPedidoEntityRepository jpaSatusPedidoEntityRepository;

    private final StatusPedidoMapper statusPedidoMapper;

    public StatutoPedidoRepositoryImpl(StatusPedidoEntityRepository jpaSatusPedidoEntityRepository, StatusPedidoMapper statusPedidoMapper) {
        this.jpaSatusPedidoEntityRepository = jpaSatusPedidoEntityRepository;
        this.statusPedidoMapper = statusPedidoMapper;
    }

    @Override
    public void save(StatusPedido statusPedido) {

        var entity = statusPedidoMapper.toEntity(statusPedido);
        jpaSatusPedidoEntityRepository.save(entity);
    }
}
