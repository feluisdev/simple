package cv.igrp.simple.utente.infrastructure.persistence.repository;

import cv.igrp.simple.utente.infrastructure.persistence.entity.UtenteServicoEntity;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import java.util.Optional;

import org.springframework.data.repository.history.RevisionRepository;

@Repository
public interface UtenteServicoEntityRepository extends
    JpaRepository<UtenteServicoEntity, Integer>,
    JpaSpecificationExecutor<UtenteServicoEntity>,
    RevisionRepository<UtenteServicoEntity, Integer, Integer>
{

}