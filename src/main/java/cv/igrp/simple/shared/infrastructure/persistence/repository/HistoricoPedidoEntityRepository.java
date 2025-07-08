package cv.igrp.simple.shared.infrastructure.persistence.repository;

import cv.igrp.simple.shared.infrastructure.persistence.entity.HistoricoPedidoEntity;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
public interface HistoricoPedidoEntityRepository extends
    JpaRepository<HistoricoPedidoEntity, Integer>,
    JpaSpecificationExecutor<HistoricoPedidoEntity>
{

    Optional<HistoricoPedidoEntity> findByHistoricoUuid(UUID historicoUuid);

    List<HistoricoPedidoEntity> findByPedidoId_PedidoUuid(UUID pedidoIdPedidoUuid);

    Optional<HistoricoPedidoEntity> findByPedidoId_PedidoUuidAndHistoricoUuid(
            UUID pedidoIdPedidoUuid, UUID historicoUuid
    );

}