package cv.igrp.simple.shared.infrastructure.persistence.repository;

import cv.igrp.simple.shared.infrastructure.persistence.entity.EstabelecimentoEntity;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.repository.history.RevisionRepository;

@Repository
public interface EstabelecimentoEntityRepository extends
    JpaRepository<EstabelecimentoEntity, Integer>,
    JpaSpecificationExecutor<EstabelecimentoEntity>,
    RevisionRepository<EstabelecimentoEntity, Integer, Integer>
{

}