package cv.igrp.simple.shared.infrastructure.persistence.repository;

import cv.igrp.simple.shared.infrastructure.persistence.entity.StatusPedidoEntity;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
public interface StatusPedidoEntityRepository extends
    JpaRepository<StatusPedidoEntity, Integer>,
    JpaSpecificationExecutor<StatusPedidoEntity>
{

    Optional<StatusPedidoEntity> findByStatusUuid(UUID statusUuid);

    boolean existsByCodigo(String codigo);

    Optional<StatusPedidoEntity> findByCodigo(String codigo);

    List<StatusPedidoEntity> findByAtivoTrue();

}