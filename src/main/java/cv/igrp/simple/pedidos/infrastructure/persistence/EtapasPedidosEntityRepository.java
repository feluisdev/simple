package cv.igrp.simple.pedidos.infrastructure.persistence;

import cv.igrp.simple.pedidos.domain.models.EtapasPedidosEntity;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import java.util.Optional;



@Repository
@RepositoryRestResource(path = "etapasPedidosEntity")
public interface EtapasPedidosEntityRepository extends
    JpaRepository<EtapasPedidosEntity, Integer>,
    JpaSpecificationExecutor<EtapasPedidosEntity>
{

}