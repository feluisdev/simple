package cv.igrp.simple.shared.infrastructure.persistence.repository;

import cv.igrp.simple.shared.infrastructure.persistence.entity.DocumentoPedidoEntity;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import java.util.Optional;
import java.util.UUID;


@Repository
public interface DocumentoPedidoEntityRepository extends
    JpaRepository<DocumentoPedidoEntity, Integer>,
    JpaSpecificationExecutor<DocumentoPedidoEntity>
{

}