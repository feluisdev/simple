package cv.igrp.simple.utente.infrastructure.persistence.repository;

import cv.igrp.simple.utente.infrastructure.persistence.entity.UtenteEntity;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import java.util.Optional;



@Repository
public interface UtenteEntityRepository extends
    JpaRepository<UtenteEntity, Integer>,
    JpaSpecificationExecutor<UtenteEntity>
{

}