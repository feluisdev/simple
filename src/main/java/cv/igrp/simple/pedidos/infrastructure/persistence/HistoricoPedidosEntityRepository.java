package cv.igrp.simple.pedidos.infrastructure.persistence;

import cv.igrp.simple.pedidos.domain.models.HistoricoPedidosEntity;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import java.util.Optional;
import java.util.UUID;


@Repository
public interface HistoricoPedidosEntityRepository extends
    JpaRepository<HistoricoPedidosEntity, Integer>,
    JpaSpecificationExecutor<HistoricoPedidosEntity>
{

}