package cv.igrp.simple.shared.infrastructure.persistence.repository;

import cv.igrp.simple.shared.infrastructure.persistence.entity.DocumentoPedidoEntity;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
public interface DocumentoPedidoEntityRepository extends
    JpaRepository<DocumentoPedidoEntity, Integer>,
    JpaSpecificationExecutor<DocumentoPedidoEntity>
{

    Optional<DocumentoPedidoEntity> findByDocumentoUuid(UUID documentoUuid);

    List<DocumentoPedidoEntity> findByPedidoId_PedidoUuid(UUID pedidoIdPedidoUuid);

    Optional<DocumentoPedidoEntity>
    findByPedidoId_PedidoUuidAndDocumentoUuid(UUID pedidoIdPedidoUuid, UUID documentoUuid);

}