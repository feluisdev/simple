package cv.igrp.simple.utente.infrastructure.persistence;

import cv.igrp.simple.utente.domain.models.UtenteServicoEntity;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.data.repository.query.Param;

@Repository
public interface UtenteServicoEntityRepository extends
    JpaRepository<UtenteServicoEntity, Integer>,
    JpaSpecificationExecutor<UtenteServicoEntity>,
    RevisionRepository<UtenteServicoEntity, Integer, Integer>
{
    @Query("SELECT us FROM UtenteServicoEntity us WHERE us.id = :servicoId AND us.utenteId.id = :utenteId")
    Optional<UtenteServicoEntity> buscarPorServicoEUtente(@Param("servicoId") Integer servicoId, @Param("utenteId") Integer utenteId);

    @Query("SELECT us FROM UtenteServicoEntity us WHERE us.utenteId.id = :utenteId")
    Page<UtenteServicoEntity> buscarServicosPorUtenteId(@Param("utenteId") Integer utenteId, Pageable pageable);

}