package cv.igrp.simple.utente.infrastructure.persistence;

import cv.igrp.simple.utente.domain.models.TipoUtenteEntity;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import java.util.Optional;



@Repository
public interface TipoUtenteEntityRepository extends
    JpaRepository<TipoUtenteEntity, Integer>,
    JpaSpecificationExecutor<TipoUtenteEntity>
{

}