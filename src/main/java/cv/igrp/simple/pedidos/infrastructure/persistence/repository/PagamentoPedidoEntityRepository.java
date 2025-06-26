package cv.igrp.simple.pedidos.infrastructure.persistence.repository;

import cv.igrp.simple.pedidos.infrastructure.persistence.entity.PagamentoPedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;



@Repository
public interface PagamentoPedidoEntityRepository extends
    JpaRepository<PagamentoPedidoEntity, Integer>,
    JpaSpecificationExecutor<PagamentoPedidoEntity>
{

}