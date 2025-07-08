package cv.igrp.simple.shared.infrastructure.persistence.repository;

import cv.igrp.simple.shared.infrastructure.persistence.entity.PagamentoPedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
public interface PagamentoPedidoEntityRepository extends
    JpaRepository<PagamentoPedidoEntity, Integer>,
    JpaSpecificationExecutor<PagamentoPedidoEntity>
{

    Optional<PagamentoPedidoEntity> findByPagamentoUuid(UUID pagamentoUuid);

    List<PagamentoPedidoEntity> findByPedidoId_PedidoUuid(UUID pedidoIdPedidoUuid);

    Optional<PagamentoPedidoEntity> findByPedidoId_PedidoUuidAndPagamentoUuid(
            UUID pedidoIdPedidoUuid, UUID pagamentoUuid
    );


}