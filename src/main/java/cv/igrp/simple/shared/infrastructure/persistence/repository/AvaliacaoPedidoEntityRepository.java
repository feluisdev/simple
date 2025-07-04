package cv.igrp.simple.shared.infrastructure.persistence.repository;

import cv.igrp.simple.shared.infrastructure.persistence.entity.AvaliacaoPedidoEntity;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
public interface AvaliacaoPedidoEntityRepository extends
    JpaRepository<AvaliacaoPedidoEntity, Integer>,
    JpaSpecificationExecutor<AvaliacaoPedidoEntity>
{

    Optional<AvaliacaoPedidoEntity> findByAvaliacaoUuid(UUID avaliacaoUuid);

    List<AvaliacaoPedidoEntity> findByPedidoId_PedidoUuid(UUID pedidoIdPedidoUuid);

    Optional<AvaliacaoPedidoEntity> findByPedidoId_PedidoUuidAndAvaliacaoUuid(
        UUID pedidoIdPedidoUuid, UUID avaliacaoUuid
    );

}