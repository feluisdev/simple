package cv.igrp.simple.pedido.infrastructure.persistence.repository;

import cv.igrp.simple.pedido.infrastructure.persistence.entity.EtapaProcessoEntity;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import java.util.Optional;



@Repository
public interface EtapaProcessoEntityRepository extends
    JpaRepository<EtapaProcessoEntity, Integer>,
    JpaSpecificationExecutor<EtapaProcessoEntity>
{

}