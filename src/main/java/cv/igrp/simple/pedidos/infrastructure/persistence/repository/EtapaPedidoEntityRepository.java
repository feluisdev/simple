package cv.igrp.simple.pedidos.infrastructure.persistence.repository;

import cv.igrp.simple.pedidos.infrastructure.persistence.entity.EtapaPedidoEntity;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import java.util.Optional;



@Repository
@RepositoryRestResource(path = "etapaPedidoEntity")
public interface EtapaPedidoEntityRepository extends
    JpaRepository<EtapaPedidoEntity, Integer>,
    JpaSpecificationExecutor<EtapaPedidoEntity>
{

}