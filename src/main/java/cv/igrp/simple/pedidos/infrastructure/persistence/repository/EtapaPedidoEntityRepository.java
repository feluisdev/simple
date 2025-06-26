package cv.igrp.simple.pedidos.infrastructure.persistence.repository;

import cv.igrp.simple.pedidos.infrastructure.persistence.entity.EtapaPedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;



@Repository
public interface EtapaPedidoEntityRepository extends
    JpaRepository<EtapaPedidoEntity, Integer>,
    JpaSpecificationExecutor<EtapaPedidoEntity>
{

}