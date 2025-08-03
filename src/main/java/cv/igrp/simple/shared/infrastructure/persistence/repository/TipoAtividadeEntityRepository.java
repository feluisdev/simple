package cv.igrp.simple.shared.infrastructure.persistence.repository;

import cv.igrp.simple.shared.infrastructure.persistence.entity.TipoAtividadeEntity;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.repository.history.RevisionRepository;

@Repository
public interface TipoAtividadeEntityRepository extends
    JpaRepository<TipoAtividadeEntity, Integer>,
    JpaSpecificationExecutor<TipoAtividadeEntity>,
    RevisionRepository<TipoAtividadeEntity, Integer, Integer>
{

    Optional<TipoAtividadeEntity> findByExternalId(UUID externalId);


}