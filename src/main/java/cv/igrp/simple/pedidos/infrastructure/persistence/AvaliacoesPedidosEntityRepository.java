package cv.igrp.simple.pedidos.infrastructure.persistence;

import cv.igrp.simple.pedidos.domain.models.AvaliacoesPedidosEntity;
import cv.igrp.simple.pedidos.domain.repository.IAvaliacoesPedidosEntityRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import java.util.Optional;
import java.util.UUID;


@Repository
public interface AvaliacoesPedidosEntityRepository extends
    JpaRepository<AvaliacoesPedidosEntity, Integer>,
    JpaSpecificationExecutor<AvaliacoesPedidosEntity>,
    IAvaliacoesPedidosEntityRepository
{
    Optional<AvaliacoesPedidosEntity> findByPedidoId(Integer pedidoId);
}