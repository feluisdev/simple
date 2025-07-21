package cv.igrp.simple.shared.infrastructure.persistence.repository;

import cv.igrp.simple.shared.infrastructure.persistence.entity.TipoEstabelecimentoEntity;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import java.util.Optional;



@Repository
public interface TipoEstabelecimentoEntityRepository extends
    JpaRepository<TipoEstabelecimentoEntity, Integer>,
    JpaSpecificationExecutor<TipoEstabelecimentoEntity>
{

}