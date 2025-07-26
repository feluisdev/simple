package cv.igrp.simple.shared.infrastructure.persistence.repository;

import cv.igrp.simple.shared.infrastructure.persistence.entity.EtapaPedidoEntity;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import java.util.Optional;
import java.util.UUID;


@Repository
public interface EtapaPedidoEntityRepository extends
    JpaRepository<EtapaPedidoEntity, Integer>,
    JpaSpecificationExecutor<EtapaPedidoEntity>
{

}