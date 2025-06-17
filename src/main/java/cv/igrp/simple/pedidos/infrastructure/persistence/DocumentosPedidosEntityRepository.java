package cv.igrp.simple.pedidos.infrastructure.persistence;

import cv.igrp.simple.pedidos.domain.models.DocumentosPedidosEntity;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import java.util.Optional;
import java.util.UUID;


@Repository
@RepositoryRestResource(path = "documentosPedidosEntity")
public interface DocumentosPedidosEntityRepository extends
    JpaRepository<DocumentosPedidosEntity, Integer>,
    JpaSpecificationExecutor<DocumentosPedidosEntity>
{

}