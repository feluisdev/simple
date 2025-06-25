package cv.igrp.simple.pedidos.infrastructure.persistence.repository;

import cv.igrp.simple.pedidos.infrastructure.persistence.entity.PagamentoPedidoEntity;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import java.util.Optional;



@Repository
@RepositoryRestResource(path = "pagamentoPedidoEntity")
public interface PagamentoPedidoEntityRepository extends
    JpaRepository<PagamentoPedidoEntity, Integer>,
    JpaSpecificationExecutor<PagamentoPedidoEntity>
{

}