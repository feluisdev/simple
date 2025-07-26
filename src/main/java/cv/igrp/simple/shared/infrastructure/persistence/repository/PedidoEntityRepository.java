package cv.igrp.simple.shared.infrastructure.persistence.repository;

import cv.igrp.simple.shared.infrastructure.persistence.entity.PedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
public interface PedidoEntityRepository extends
    JpaRepository<PedidoEntity, Integer>,
    JpaSpecificationExecutor<PedidoEntity>
{

    Optional<PedidoEntity> findByCodigoAcompanhamento(String codigoAcompanhamento);

    List<PedidoEntity> findAllByUtenteId_Id(Integer utenteIdId);

    Optional<PedidoEntity> findByPedidoUuid(UUID pedidoUuid);
}